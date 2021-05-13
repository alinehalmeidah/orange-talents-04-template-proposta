package br.com.orangetalents.MultiServico.proposta;

public class PropostaElegivelEvent{

    Proposta propostaElegivel;

    public PropostaElegivelEvent(Proposta propostaElegivel) {
        this.propostaElegivel = propostaElegivel;
    }

    public Proposta getPropostaElegivel() {
        return propostaElegivel;
    }
}