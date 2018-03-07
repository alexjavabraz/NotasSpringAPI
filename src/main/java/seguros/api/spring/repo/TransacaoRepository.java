package seguros.api.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import seguros.api.domain.Transacao;

@Repository
public interface TransacaoRepository  extends JpaRepository<Transacao, Long>{
	

}
