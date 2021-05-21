package br.com.orangetalents.MultiServico.carteira;

import br.com.orangetalents.MultiServico.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteiraRepository extends JpaRepository<Carteira, String> {

    boolean existsByCartaoAndCarteira(Cartao cartao, TipoCarteira carteira);

}
