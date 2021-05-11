package br.com.orangetalents.MultiServico.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://127.0.0.1:9999/api/solicitacao", name = "analise")
public interface AnaliseClienteFeing {

    @PostMapping
    public AnaliseResultadoResponse analise (AnaliseSolicitacaoRequest solicitacaoRequest);
}
