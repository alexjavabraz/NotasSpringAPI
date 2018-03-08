package notas.api.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import notas.api.domain.Transacao;

@Repository
public interface TransacaoRepository  extends JpaRepository<Transacao, Long>{
	

}
