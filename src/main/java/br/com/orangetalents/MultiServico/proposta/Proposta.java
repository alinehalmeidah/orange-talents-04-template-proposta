package br.com.orangetalents.MultiServico.proposta;

import br.com.orangetalents.MultiServico.validacao.CPFouCNPJ;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
@Table
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank @CPFouCNPJ
	private String documento;

	@NotBlank @Email
	@Column (unique = true)
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@Positive @NotNull
	private BigDecimal salario;

	@Enumerated(EnumType.STRING )
	private PropostaResultado resultado = PropostaResultado.NAO_ANALISADO;

	private String idCartao;

	@Deprecated
	public Proposta() {
	}

	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
					@NotBlank String endereco, @Positive @NotNull BigDecimal salario) {

		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}


	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public PropostaResultado getResultado() {
		return resultado;
	}


	public void setResultado(PropostaResultado resultado) {
		this.resultado = resultado;
	}
}

//		Para realizar as pesquisas no banco de dados usamos o metodo findbyId { já existe por Default }
//		É usado o Optional pois ele pode ou não existir conforme a pesquisa realizada no banco de dados.
//		O Collection é usado caso a pesquisa for com mais de um atributo no parametro. { Ex findByNameAndCpd (name e cpf) }