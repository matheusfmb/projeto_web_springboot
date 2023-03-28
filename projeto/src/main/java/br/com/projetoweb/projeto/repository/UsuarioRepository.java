package br.com.projetoweb.projeto.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.projetoweb.projeto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
	
	Optional<Usuario> findByEmailAndSenha(String email, String senha);
	Optional<Usuario> findByEmail(String email);
}
