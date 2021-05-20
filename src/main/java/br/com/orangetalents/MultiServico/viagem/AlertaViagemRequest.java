package br.com.orangetalents.MultiServico.viagem;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class AlertaViagemRequest {

    @NotBlank
    private String destino;

    @Future
    private LocalDate dataTerminoViagem;

    public AlertaViagemRequest(@NotBlank String destino, @Future LocalDate dataTerminoViagem) {

        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getDataTerminoViagem() {
        return dataTerminoViagem;
    }

    public AlertaViagem toModel(Cartao cartao, String ipCliente, String userAgent) {
        return new AlertaViagem(destino, dataTerminoViagem, ipCliente, userAgent, cartao);
    }
}
