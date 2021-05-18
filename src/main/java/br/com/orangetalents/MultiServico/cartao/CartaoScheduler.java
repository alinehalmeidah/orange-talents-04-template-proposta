package br.com.orangetalents.MultiServico.cartao;

import br.com.orangetalents.MultiServico.proposta.PropostaRepository;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CartaoScheduler {

    @Autowired
    private CartaoClienteFeing cartaoClienteFeing;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;
    private Logger logger = LoggerFactory.getLogger(CartaoScheduler.class);

    @Scheduled(fixedRateString = "${atualizacao.scheduler}")
    public void verificaSituacaoNoCartao() {

        System.out.println("Entrou");

        var propostasAprovadas = propostaRepository.findAll();

        for (var proposta : propostasAprovadas) {
            if(cartaoRepository.findByPropostaId(proposta.getId()).isEmpty()) {
                try {
                    var response = cartaoClienteFeing.consultaCartao(proposta.getId());
                    var cartao = response.toModel(proposta);
                    cartaoRepository.save(cartao);

                    logger.info("Cartão Salvo");

                } catch (FeignException exception) {
                    System.out.println(exception.getMessage());
                    logger.info("Exceção: Cartao - CartaoClienteFeing");
                }
            }

        }

    }
}
