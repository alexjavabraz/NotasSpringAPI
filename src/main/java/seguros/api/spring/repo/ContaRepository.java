package seguros.api.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import seguros.api.domain.Conta;

/**
 * 
 * @author alex.braz
 *
 */
@Repository
public interface ContaRepository  extends JpaRepository<Conta, Long>{
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Conta findById(Long id);
	
	

}