package br.com.orangetalents.MultiServico.viagem;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AlertaViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String destino;

    @NotNull
    @CreationTimestamp
    private LocalDateTime instanteAvisoViagem = LocalDateTime.now();

    @NotNull
    @Future
    private LocalDate dataTerminoViagem;

    @NotBlank
    private String ipCliente;
    @NotBlank
    private String userAgent;

    @NotNull
    @ManyToOne()
    private Cartao cartao;

    public AlertaViagem(@NotBlank String destino, @Future LocalDate dataTerminoViagem, @NotBlank String ipCliente,
                            @NotBlank String userAgent, Cartao cartao) {

        this.destino = destino;
        this.dataTerminoViagem = dataTerminoViagem;
        this.ipCliente = ipCliente;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    @Deprecated
    public AlertaViagem()
    {}

    public Long getId() {
        return id;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDateTime getInstanteAvisoViagem() {
        return instanteAvisoViagem;
    }

    public LocalDate getDataTerminoViagem() {
        return dataTerminoViagem;
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