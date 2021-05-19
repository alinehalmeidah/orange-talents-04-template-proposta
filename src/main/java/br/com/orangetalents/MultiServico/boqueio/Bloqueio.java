package br.com.orangetalents.MultiServico.boqueio;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @CreationTimestamp
    private LocalDateTime instanteBloqueio = LocalDateTime.now();
    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;
    @NotNull
    @OneToOne
    private Cartao cartao;

    public Bloqueio(@NotBlank String ipCliente,
                    @NotBlank String userAgent, Cartao cartao) {

        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    @Deprecated
    public Bloqueio() {
    }


    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteBloqueio() {
        return instanteBloqueio;
    }

    public String getIpCliente() {
        return ipCliente;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public Cartao getCartao() {
        return cartao;
    }
}