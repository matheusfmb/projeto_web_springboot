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
@CrossOrigin
@RequestMapping("/usuarios")
public class UsuarioController{
	@Autowired
	private UsuarioRepository dao;
	
	@GetMapping
	public List<Usuario> listaUsuarios(){
		return (List<Usuario>) dao.findAll();		
	}
	
	@PostMapping
	public Usuario criarUsuario(@RequestBody Usuario usuario) {
		Usuario usuarioNovo = dao.save(usuario);
		return usuarioNovo;
	}
	
	@PutMapping 
	public Usuario EditarUsuario (@RequestBody Usuario usuario) {
		Usuario usuarioEditado = dao.save(usuario);
		return usuarioEditado;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluirUsuario(@PathVariable Integer id) {
	    Optional<Usuario> usuarioExcluido = dao.findById(id);
	    if (usuarioExcluido.isPresent()) {
	        dao.deleteById(id);
	        return ResponseEntity.ok("Usuário excluído com sucesso.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
	    }
	}
}
