package br.com.orangetalents.MultiServico.biometria;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
@Table(name = "biometria")
public class Biometria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String fingerPrint;

    @NotNull
    @ManyToOne
    private Cartao cartao;

    @NotNull
    @CreationTimestamp
    private LocalDateTime cadastradaEm = LocalDateTime.now();

    @Deprecated
    public Biometria() {

    }

    public Biometria(@NotNull String fingerPrint, @NotNull Cartao cartao) {

        this.fingerPrint = fingerPrint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public LocalDateTime getCadastradaEm() {
        return cadastradaEm;
    }



}