package br.com.orangetalents.MultiServico.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, String> {
    Optional<Cartao> findByPropostaId(Long idProposta);
}
