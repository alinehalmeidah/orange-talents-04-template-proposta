package br.com.orangetalents.MultiServico.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartao", url = "${cartao.host}")
@Component
public interface CartaoClienteFeing {

    @GetMapping("/api/cartoes?idProposta")
    public CartaoResponse consultaCartao(@RequestParam("idProposta") Long idProposta);
}

