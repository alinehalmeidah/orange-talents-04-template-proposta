package br.com.orangetalents.MultiServico.proposta;

import br.com.orangetalents.MultiServico.analise.AnaliseClienteFeing;
import br.com.orangetalents.MultiServico.analise.AnaliseSolicitacaoRequest;
import br.com.orangetalents.MultiServico.compartilhado.UniqueValueValidator;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private  PropostaRepository propostaRepository;

	private Logger logger = LoggerFactory.getLogger(UniqueValueValidator.class);

	@Autowired
	private AnaliseClienteFeing analiseClienteFeing;


	@PostMapping
	@Transactional
	public ResponseEntity<?> criaCadastro(@Valid @RequestBody PropostaRequest request, UriComponentsBuilder builder) {
		Proposta  proposta = request.toModel();
		propostaRepository.save(proposta);
		consultaFinanceira(proposta);
		propostaRepository.save(proposta);

		return ResponseEntity
				.created(builder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri()).build();
	}

	public void consultaFinanceira(Proposta proposta){
		PropostaResultado resultado;
		try{
			logger.info(" proposta {} enviada para analise", proposta.getId());
			analiseClienteFeing.analise(new AnaliseSolicitacaoRequest(proposta));
			resultado = PropostaResultado.ELEGIVEL;
		}catch (FeignException.UnprocessableEntity e){
			logger.error("Proposta numero {} com restrição financeira.", proposta.getId());
			resultado = PropostaResultado.NAO_ELEGIVEL;
		}
		proposta.setResultado(resultado);
	}


}