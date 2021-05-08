package br.com.orangetalents.MultiServico.proposta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
	Optional<Proposta> findById(Long id);

}
