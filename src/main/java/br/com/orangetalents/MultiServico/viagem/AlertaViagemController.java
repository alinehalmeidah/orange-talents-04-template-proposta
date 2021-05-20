package br.com.orangetalents.MultiServico.viagem;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import br.com.orangetalents.MultiServico.cartao.CartaoClienteFeing;
import br.com.orangetalents.MultiServico.cartao.CartaoRepository;
import br.com.orangetalents.MultiServico.compartilhado.ExecutorTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/viagem")
public class AlertaViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ExecutorTransacao executorTransacao;
    @Autowired
    private CartaoClienteFeing cartaoClienteFeing;

    private final Logger logger = LoggerFactory.getLogger(AlertaViagemController.class);

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> novoAvisoViagem(@PathVariable("idCartao") String id,
                                             HttpServletRequest servletRequest,
                                             @RequestBody @Valid AlertaViagemRequest request){

        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
            logger.warn("Cartão {} inesxistente=", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }

        try {
            AlertaViagem novoAlertaViagem = request.toModel(cartao.get(), servletRequest.getLocalAddr(),
                    servletRequest.getHeader("User-Agent"));
            cartaoClienteFeing.avisarViagem(cartao.get().getId(), request);
            cartao.get().setViagem(novoAlertaViagem);
            executorTransacao.atualizaEComita(cartao.get());
            logger.info("Viagem confirmada com sucesso!");
        } catch (Exception exception) {
            logger.error("Algo de errado não está certo {}", exception.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
        return ResponseEntity.ok().build();
    }
}
