package br.com.projetoweb.projeto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoweb.projeto.DAO.UsuarioRepository;
import br.com.projetoweb.projeto.model.Usuario;

@RestController
public class UsuarioController{
	@Autowired
	private UsuarioRepository dao;
	
	@GetMapping("/usuarios")
	public List<Usuario> listaUsuarios(){
		return (List<Usuario>) dao.findAll();		
	}
}
