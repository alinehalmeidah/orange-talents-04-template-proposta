package br.com.orangetalents.MultiServico.biometria;


import br.com.orangetalents.MultiServico.cartao.Cartao;
import br.com.orangetalents.MultiServico.cartao.CartaoRepository;
import br.com.orangetalents.MultiServico.compartilhado.ExecutorTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private ExecutorTransacao executorTransacao;

    private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> salvarBiometria(@PathVariable("id") Long id,
                                             @RequestBody @Valid BiometriaRequest request,
                                             UriComponentsBuilder uriBuilder  ){
        Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(!cartao.isPresent()) {
            logger.warn("Cartão não identificado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
        }

        Biometria biometria = request.toModel(cartao.get());
        executorTransacao.salvaEComita(biometria);
        logger.info("Biometria cadastrada {}", cartao.get().getId());
        URI uri = uriBuilder.path("/biometria/{id}").build(biometria.getId());
        return ResponseEntity.created(uri).build();
    }

}
