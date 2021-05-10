package br.com.orangetalents.MultiServico.proposta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;

	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

	@PostMapping
	public ResponseEntity<?> criaCadastro(@Valid @RequestBody PropostaRequest request,
										  UriComponentsBuilder builder) {

		logger.info("Criando a Proposta");

		if (repository.existsByDocumento(request.getDocumento())){

			logger.warn("Proposta não foi criada");

			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Esse procedimento não foi concluido! Pois. já existe esse documento!");
		}

		Proposta novaProposta = request.toModel();

		novaProposta = repository.save(novaProposta);

		logger.info("Proposta criada com sucesso!", novaProposta.getDocumento());

		return ResponseEntity
				.created(builder.path("/propostas/{id}").buildAndExpand(novaProposta.getId()).toUri()).build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> consultar(@PathVariable("id")long id){
		logger.info("Consultando Proposta");

		Optional<Proposta> proposta = repository.findById(id);

		if(proposta.isPresent()){
			logger.info("Proposta encontrada");
			return ResponseEntity.ok(proposta);
		}
		logger.warn("Proposta não encontrada");
		return ResponseEntity.notFound().build();
	}
}