package br.com.orangetalents.MultiServico.analise;

public class AnaliseResultadoResponse {

    private String documento;
    private String nome;
    private Long idProposta;

    private AnaliseResultado analiseResultado;

    public AnaliseResultadoResponse(String documento, String nome, Long idProposta,AnaliseResultado analiseResultado){
        this.documento = documento;
        this.nome = nome;
        this.idProposta = idProposta;
        this.analiseResultado = analiseResultado;
    }

    public AnaliseResultado getAnaliseResultado() {
        return analiseResultado;
    }

}
