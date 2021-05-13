package br.com.orangetalents.MultiServico.cartao;

import br.com.orangetalents.MultiServico.proposta.Proposta;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name ="cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;

    @NotBlank
    private String numero;

    @NotNull
    private Integer limite;

    @NotNull @OneToOne @JoinColumn(name = "proposta")
    private Proposta proposta;

    @Enumerated(value = EnumType.STRING)
    private StatusCartao status = StatusCartao.ATIVO;

    @Deprecated
    public Cartao() {
    }

    public Cartao(@NotNull LocalDateTime emitidoEm, @NotBlank String titular, @NotBlank String numero,
                       @NotNull Integer limite, @NotNull Proposta proposta) {

        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.numero = numero;
        this.limite = limite;
        this.proposta = proposta;

    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public String getNumero() {
        return numero;
    }

    public Integer getLimite() {
        return limite;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public StatusCartao getStatus() {
        return status;
    }


}