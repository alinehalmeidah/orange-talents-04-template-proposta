package br.com.orangetalents.MultiServico.cartao;

import br.com.orangetalents.MultiServico.boqueio.Bloqueio;
import br.com.orangetalents.MultiServico.proposta.Proposta;
import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name ="cartao")
public class Cartao {

    @Id
    private String id;
    @NotNull
    private LocalDateTime emitidoEm;
    @NotBlank
    private String titular;

    @NotNull
    private Integer limite;

    @NotNull @OneToOne @JoinColumn(name = "proposta")
    private Proposta proposta;

    @Enumerated(value = EnumType.STRING)
    private StatusCartao status = StatusCartao.ATIVO;

    @OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Bloqueio bloqueio;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, @NotNull LocalDateTime emitidoEm, @NotBlank String titular,
                  @NotNull Integer limite, @NotNull Proposta proposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.limite = limite;
        this.proposta = proposta;

    }

       public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
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

    public boolean verificaBloqueado() {
        return this.status.equals(StatusCartao.BLOQUEADO);
    }

    public void setBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
        this.status = StatusCartao.BLOQUEADO;
    }


}