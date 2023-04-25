package br.com.projetoweb.projeto.service;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.projetoweb.projeto.Security.Token;
import br.com.projetoweb.projeto.Security.TokenUtil;
import br.com.projetoweb.projeto.dto.UsuarioCadastroDTO;
import br.com.projetoweb.projeto.dto.UsuarioLogadoDTO;
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

	public ResponseEntity<String> criarUsuario(UsuarioCadastroDTO usuarioDTO) {
		Optional<Usuario> isUserAlreadyCreated = repository.findByEmail(usuarioDTO.getEmail());
		if(isUserAlreadyCreated.isPresent()){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Esse email ja foi cadastrado");
		}else{
			String encoder = passwordEncoder.encode(usuarioDTO.getSenha());
			Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getTelefone(), encoder);
			repository.save(usuario);
			Optional<Usuario> isUserCreated = repository.findByEmail(usuario.getEmail());
			if(isUserCreated.isPresent()){
				return ResponseEntity.status(HttpStatus.CREATED).body("Usuario cadastrado com sucesso");
			}else{
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Usuario não foi cadastrado com Sucesso");
			}
		}
	}
	
	public Usuario editarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioEditado = repository.save(usuario);
		return usuarioEditado;
	}
	
	public ResponseEntity <?> deletarUsuario(Integer id) {
		Optional<Usuario> usuario = repository.findById(id);
		if (usuario.isPresent()){
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Usuário deletado com sucesso!");
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não econtrado");
		}
	}
	
	public ResponseEntity<?> loginUsuario(UsuarioLoginDTO usuario) {	
		String email = usuario.getEmail();
		String senha = usuario.getSenha();
		Optional<Usuario> loginUsuario = repository.findByEmail(email);
		if (loginUsuario.isPresent()) {
		    if (passwordEncoder.matches(senha, loginUsuario.get().getSenha())) {
					String tokenAuth = TokenUtil.createToken(loginUsuario.get().getEmail());
					Token token = new Token(tokenAuth);
					UsuarioLogadoDTO usuarioLogadoDTO = new UsuarioLogadoDTO(loginUsuario.get().getId(),loginUsuario.get().getEmail(),loginUsuario.get().getNome(),loginUsuario.get().getTelefone());
					JSONObject responseJson = new JSONObject(usuarioLogadoDTO);
					responseJson.put("token", tokenAuth);
					String responseBody = responseJson.toString();
					return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.AUTHORIZATION,token.getToken()).body(responseBody);
			}else{
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou Senha incorretos!");
			}
		}else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não Cadastrado");
		}
	}
}
