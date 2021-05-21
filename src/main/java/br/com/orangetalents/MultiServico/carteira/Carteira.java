package br.com.orangetalents.MultiServico.carteira;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Carteira {
    @Id
    private String id;
    @NotBlank
    private String idAssociacao;
    @NotBlank
    private String email;
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoCarteira carteira;
    @ManyToOne
    private Cartao cartao;

    public Carteira(@NotBlank String idAssociacao, @NotBlank String email, @NotNull TipoCarteira carteira,
                         Cartao cartao) {
        this.idAssociacao = idAssociacao;
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
    }

    @Deprecated
    public Carteira() {
    }

    public String getId() {
        return id;
    }
    public String getIdAssociacao() {
        return idAssociacao;
    }
    public String getEmail() {
        return email;
    }
    public TipoCarteira getCarteira() {
        return carteira;
    }
    public Cartao getCartao() {
        return cartao;
    }



}