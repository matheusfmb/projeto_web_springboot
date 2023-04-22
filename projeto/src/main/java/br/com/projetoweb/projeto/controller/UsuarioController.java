package br.com.projetoweb.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetoweb.projeto.dto.UsuarioCadastroDTO;
import br.com.projetoweb.projeto.dto.UsuarioLoginDTO;
import br.com.projetoweb.projeto.model.Usuario;
import br.com.projetoweb.projeto.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "*")
public class UsuarioController{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	@Operation(summary = "Retorna todos os usuário cadastrados")
	public ResponseEntity <List<Usuario>> listaUsuarios(){
		return ResponseEntity.status(200).body(usuarioService.listarUsuario());
	}
	
	
	@PostMapping("/usuarios/login")
	@Operation(summary = "Realiza o login de um usuário")
	@ApiResponses(value = {
	  @ApiResponse(responseCode = "200", description ="Usuário autenticado com sucesso"),
	  @ApiResponse(responseCode = "401", description ="Usuário Não autorizado"),
	  @ApiResponse(responseCode = "404", description ="Usuário não Encontrado"),
	  @ApiResponse(responseCode = "500", description ="Server Error")
	})
	public ResponseEntity<?> loginUsuario(@Valid @RequestBody UsuarioLoginDTO usuario) {
	  ResponseEntity<?> response;
	  try {          
	    response = usuarioService.loginUsuario(usuario);
	  } catch (Exception e) {
	    response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SEVER_ERROR");
	  }
	  return response;
	}
	
	@PostMapping("/usuarios")
	@Operation(summary = "Cadastra novo Usuário no Banco de dados")
	public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioCadastroDTO usuario) {
		ResponseEntity<?> response;
		try {
			response = usuarioService.criarUsuario(usuario);
		}catch (Exception e){
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR");

		}
		return response;
	}
	
	

	@PutMapping("/usuarios")
	@Operation(summary = "Edita o cadastro do usuário")
	public ResponseEntity<Usuario> EditarUsuario (@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(200).body(usuarioService.editarUsuario(usuario));
	}
	
	
	//Padrão é 204. no context
	@DeleteMapping("/usuarios/{id}")
	@Operation(summary = "Exlcui o cadastro do usuário")
	public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
	    usuarioService.deletarUsuario(id);
	    return ResponseEntity.status(204).build();	
	}
	
	
	//Tratando 404 vindos do FRONT
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName,errorMessage);
			errors.put(fieldName,errorMessage);
			});
		return errors;
		
	}
	
}
