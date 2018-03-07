package seguros.api.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import seguros.api.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Usuario findById(Long id);
  
  Usuario findByNomeAndPassword(String nome, String password);
  
  Usuario findByNome(String nome);
  
  Usuario findByEmail(String email);

}
