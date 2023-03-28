package br.com.projetoweb.projeto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.projetoweb.projeto.model.Usuario;
import br.com.projetoweb.projeto.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	
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
		Usuario usuarioEditado = repository.save(usuario);
		return usuarioEditado;
	}
	
	public Boolean deletarUsuario(Integer id) {
		repository.deleteById(id);
		return true;
		
	}
	
}
