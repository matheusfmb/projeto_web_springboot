package br.com.projetoweb.projeto.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoweb.projeto.model.Usuario;
import br.com.projetoweb.projeto.repository.UsuarioRepository;
import br.com.projetoweb.projeto.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController{
	
	@Autowired
	private UsuarioRepository dao;
	
	@Autowired
	private UsuarioService usuarioService;

	
	@GetMapping("/usuarios")
	public ResponseEntity <List<Usuario>> listaUsuarios(){
		return ResponseEntity.status(200).body(usuarioService.listarUsuario());
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Usuario usuario) {
	    Optional<Usuario> usuarioAutenticado = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
	    if (!usuarioAutenticado.isPresent()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos");
	    } else {
	        return ResponseEntity.status(HttpStatus.OK).body(usuarioAutenticado);
	    }
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity <Usuario> criarUsuario(@RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(usuario));
	}
	
	@PutMapping("/usuarios")
	public ResponseEntity<Usuario> EditarUsuario (@RequestBody Usuario usuario) {
		return ResponseEntity.status(200).body(usuarioService.editarUsuario(usuario));
	}
	
	
	//Padrão é 204. no context
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
	    usuarioService.deletarUsuario(id);
	    return ResponseEntity.status(204).build();	
	    
	}
}
