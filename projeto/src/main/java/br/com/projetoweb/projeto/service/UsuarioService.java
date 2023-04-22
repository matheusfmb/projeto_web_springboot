package br.com.projetoweb.projeto.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.projetoweb.projeto.Security.Token;
import br.com.projetoweb.projeto.Security.TokenUtil;
import br.com.projetoweb.projeto.dto.UsuarioCadastroDTO;
import br.com.projetoweb.projeto.dto.UsuarioLoginDTO;
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

	public ResponseEntity<Usuario> criarUsuario(UsuarioCadastroDTO usuarioDTO) {
		String encoder = passwordEncoder.encode(usuarioDTO.getSenha());
		Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getTelefone(), encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNovo);
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
	
	public ResponseEntity<?> loginUsuario(UsuarioLoginDTO usuario) {	
		String email = usuario.getEmail();
		String senha = usuario.getSenha();
		Optional<Usuario> loginUsuario = repository.findByEmail(email);
		if (loginUsuario.isPresent()) {
		    if (passwordEncoder.matches(senha, loginUsuario.get().getSenha())) {
				String tokenAuth = TokenUtil.createToken(loginUsuario.get().getEmail());
				Token token = new Token(tokenAuth);
				return ResponseEntity.ok().header("Authorization",token.getToken()).body(loginUsuario);
		    }else{
		    	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta");
		    }
		 }else{
		    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
		  }
		}
}
