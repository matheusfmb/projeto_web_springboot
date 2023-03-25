package br.com.projetoweb.projeto.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.projetoweb.projeto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	

}
