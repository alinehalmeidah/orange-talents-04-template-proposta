package br.com.orangetalents.MultiServico.boqueio;

import javax.validation.constraints.NotBlank;

public class BloqueioRequest {
    @NotBlank
    private String sistemaResponsavel;

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
