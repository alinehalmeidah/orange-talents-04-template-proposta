package br.com.orangetalents.MultiServico.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "analiseProposta", url = "${analise.host}")
public interface AnaliseClienteFeing {

    @PostMapping
    public AnaliseResultadoResponse analise (AnaliseSolicitacaoRequest solicitacaoRequest);
}
