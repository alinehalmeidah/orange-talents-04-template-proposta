package br.com.orangetalents.MultiServico.proposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;

	@PostMapping
	public ResponseEntity<?> criaCadastro(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder builder) {
		Proposta novaProposta = request.toModel();
		novaProposta = repository.save(novaProposta);

		return ResponseEntity
				.created(builder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri()).build();
	}
}