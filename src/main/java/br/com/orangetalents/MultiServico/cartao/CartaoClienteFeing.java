package br.com.orangetalents.MultiServico.cartao;

import br.com.orangetalents.MultiServico.boqueio.BloqueioRequest;
import br.com.orangetalents.MultiServico.carteira.CarteiraRequest;
import br.com.orangetalents.MultiServico.carteira.CarteiraResponse;
import br.com.orangetalents.MultiServico.viagem.AlertaViagemRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@FeignClient(value = "cartao", url = "${cartao.host}")
@Component
public interface CartaoClienteFeing {

    @GetMapping("/api/cartoes?idProposta")
    public CartaoResponse consultaCartao(@RequestParam("idProposta") Long idProposta);

    @PostMapping("/{id}/bloqueios")
    public void bloqueioCartao(@PathVariable String id, @RequestBody @Valid BloqueioRequest request);

    //------------> Aviso viagem abaixo <---------

    @PostMapping("/{id}/avisos")
    public void avisarViagem(@PathVariable String id, @RequestBody @Valid AlertaViagemRequest request);

    @PostMapping("/{id}/carteiras")
    public CarteiraResponse associarCarteira(@PathVariable String id, @RequestBody CarteiraRequest request);
}

