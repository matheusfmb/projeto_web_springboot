package br.com.projetoweb.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.projetoweb.projeto.model.Usuario;
import br.com.projetoweb.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public List<Usuario> listarUsuario(){
		List<Usuario> lista = repository.findAll();
		return lista;
	}

	public Usuario criarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;
	}
	
	public Usuario editarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioEditado = repository.save(usuario);
		return usuarioEditado;
	}
	
	public Boolean deletarUsuario(Integer id) {
		repository.deleteById(id);
		return true;
		
	}
	public ResponseEntity<?> loginUsuario(Usuario usuario) {
		Optional<Usuario> loginUsuario = repository.findByEmail(usuario.getEmail());
		if (loginUsuario.isPresent()) {
			if (passwordEncoder.matches(usuario.getSenha(), loginUsuario.get().getSenha())){
				return ResponseEntity.status(HttpStatus.OK).body(loginUsuario);
			}else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
			}
		}else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não encontrado");
		}
		
	}
	
}
