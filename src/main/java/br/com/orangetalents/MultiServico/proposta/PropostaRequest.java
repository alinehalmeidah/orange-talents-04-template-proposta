package br.com.orangetalents.MultiServico.proposta;


import br.com.orangetalents.MultiServico.validacao.CPFouCNPJ;
import br.com.orangetalents.MultiServico.compartilhado.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class PropostaRequest {

	@NotBlank @CPFouCNPJ
	@UniqueValue(targetClass = Proposta.class, campo = "documento", message = "CPF ou CNPJ inválido, o valor deve ser único!")
	private String documento;

	@NotBlank
	@Email
	@UniqueValue(targetClass = Proposta.class, campo = "email", message = "E-mail inválido, o valor deve ser único!")
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@Positive
	@NotNull
	private BigDecimal salario;

	@Deprecated
	public PropostaRequest() {}

	public PropostaRequest(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
						   @NotBlank String endereco, @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
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

	public Proposta toModel() {
		return new Proposta(this.documento, this.email, this.endereco, this.nome, this.salario);
	}
}