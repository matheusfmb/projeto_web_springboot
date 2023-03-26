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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.projetoweb.projeto.DAO.UsuarioRepository;
import br.com.projetoweb.projeto.model.Usuario;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController{
	
	@Autowired
	private UsuarioRepository dao;

	
	@GetMapping("/usuarios")
	public ResponseEntity <List<Usuario>> listaUsuarios(){
		List<Usuario> lista = (List<Usuario>) dao.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(lista);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Usuario usuario) {
	    Optional<Usuario> usuarioAutenticado = dao.findByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
	    if (!usuarioAutenticado.isPresent()) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos");
	    } else {
	        return ResponseEntity.ok("Login bem-sucedido");
	    }
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity <Usuario> criarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioNovo = dao.save(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioNovo);
	}
	
	@PutMapping("/usuarios")
	public ResponseEntity<Usuario> EditarUsuario (@RequestBody Usuario usuario) {
		Usuario usuarioEditado = dao.save(usuario);
		return ResponseEntity.status(HttpStatus.OK).body(usuarioEditado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
	    Optional<Usuario> usuarioExcluido = dao.findById(id);
	    if (usuarioExcluido.isPresent()) {
	        dao.deleteById(id);
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
	
	
}
