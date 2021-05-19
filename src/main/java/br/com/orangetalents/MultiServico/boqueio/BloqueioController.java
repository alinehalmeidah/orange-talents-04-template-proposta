package br.com.orangetalents.MultiServico.boqueio;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import br.com.orangetalents.MultiServico.cartao.CartaoClienteFeing;
import br.com.orangetalents.MultiServico.cartao.CartaoRepository;
import br.com.orangetalents.MultiServico.compartilhado.ExecutorTransacao;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ExecutorTransacao executorTransacao;
    @Autowired
    private CartaoClienteFeing cartaoClienteFeing;

    private final Logger logger = LoggerFactory.getLogger(BloqueioController.class);

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloquearCartao(@PathVariable("idCartao") String id,
                                            HttpServletRequest servletRequest,
                                            @RequestBody @Valid BloqueioRequest request){

        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            logger.warn("Cartão {} inexistente=", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão inexistente");
        }
        if(cartao.get().verificaBloqueado()) {
            logger.warn("Cartão {} já está bloqueado=", id);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já está bloqueado");
        }

        Bloqueio bloqueio = new Bloqueio(servletRequest.getLocalAddr(),
                servletRequest.getHeader("User-Agent"), cartao.get());
        bloquearCartao(bloqueio, cartao.get(), request);
        logger.info("Cartão bloqueado com sucesso");
        return ResponseEntity.ok().build();
    }

    private void bloquearCartao(Bloqueio bloqueio, Cartao cartao, BloqueioRequest request) {
        try {
            cartaoClienteFeing.bloqueioCartao(cartao.getId(), request);
            cartao.setBloqueio(bloqueio);
            executorTransacao.atualizaEComita(cartao);
        } catch (FeignException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Bloqueio do Cartão não foi realizado");
        }

    }

}
