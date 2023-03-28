package br.com.projetoweb.projeto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.projetoweb.projeto.model.Usuario;
import br.com.projetoweb.projeto.service.UsuarioService;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController{
	
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	public PasswordEncoder passwordEncoder;

	
	@GetMapping("/usuarios")
	public ResponseEntity <List<Usuario>> listaUsuarios(){
		return ResponseEntity.status(200).body(usuarioService.listarUsuario());
	}
	
	 @PostMapping("/login")
	 public ResponseEntity<?> loginUsuario(@RequestBody Usuario usuario) {
	     ResponseEntity<?> response;
	     try {	    	 	 
	         response = usuarioService.loginUsuario(usuario);
	         } catch (Exception e) {
	        	 response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao realizar login");
	        }
	        return response;
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
