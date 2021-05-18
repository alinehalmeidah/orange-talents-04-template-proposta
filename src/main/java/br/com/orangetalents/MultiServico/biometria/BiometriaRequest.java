package br.com.orangetalents.MultiServico.biometria;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.util.Base64;

public class BiometriaRequest {

    @NotNull
    private String fingerPrint;

    public BiometriaRequest(@NotNull @JsonProperty("fingerPrint") String fingerPrint) {

        this.fingerPrint = fingerPrint;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Biometria toModel(Cartao cartao) {
        String biometria = Base64.getEncoder().encodeToString(fingerPrint.getBytes());
        return new Biometria(biometria, cartao);
    }
}