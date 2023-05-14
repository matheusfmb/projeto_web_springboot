package br.com.projetoweb.projeto.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.servlet.ModelAndView;
import br.com.projetoweb.projeto.dto.UsuarioCadastroDTO;
import br.com.projetoweb.projeto.dto.UsuarioLogadoDTO;
import br.com.projetoweb.projeto.dto.UsuarioLoginDTO;
import br.com.projetoweb.projeto.model.Usuario;
import br.com.projetoweb.projeto.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;



@RestController
@CrossOrigin(origins = "*")
public class UsuarioController{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Operation(summary = "Descrição do meu endpoint", responses = { @ApiResponse(responseCode = "200", description = "OK") })
	// Existe algum problema na documentação desse EndPoint, pelo jeito o SpringDoc OpenAPI não consegue mapear esse endpoint... talvez por causa do retorno ModelAndView?
	@GetMapping("/api/usuarios")
	public ModelAndView listarUsuarios() {
	List<Usuario> usuarios = usuarioService.listarUsuarios();
	ModelAndView mv = new ModelAndView("usuarios");
	mv.addObject("usuarios", usuarios);
	return mv;
	}

	@GetMapping("/api/usuarios/teste---")
	@Operation(summary = "Retorna todos os usuário cadastrados")
	@ApiResponses(value = {
	@ApiResponse(responseCode = "200", description ="Sucesso na Consulta"),
	@ApiResponse(responseCode = "401", description ="Não autorizado"),
	@ApiResponse(responseCode = "500", description ="Server Error")
	})
	public List<Usuario> listarUsuariosTeste() {
	List<Usuario> usuarios = usuarioService.listarUsuarios();
	return usuarios;
	}
	
	@PostMapping("/api/usuarios/login")
	@Operation(summary = "Realiza Login de Usuário", 
	requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com os dados do usuário a ser logado", 
	required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)))
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description ="Usuário autenticado com sucesso", content = @Content(mediaType = "application/json")),
		@ApiResponse(responseCode = "401", description ="Usuário Não autorizado", content = @Content(mediaType = "text/plain")),
		@ApiResponse(responseCode = "404", description ="Usuário não Encontrado", content = @Content(mediaType = "text/plain")),
		@ApiResponse(responseCode = "500", description ="Server Error", content = @Content(mediaType = "text/plain"))
	})
	public ResponseEntity<?> loginUsuario(@Valid @RequestBody UsuarioLoginDTO usuarioDto) {
	  ResponseEntity<?> response;
	  try {          
		return response = usuarioService.loginUsuario(usuarioDto);
	  } catch (Exception e) {
	    response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SEVER_ERROR");
	  }
	  return response;
	}
	
	@PostMapping("/api/usuarios")
	@Operation(summary = "Cadastra um novo usuário",
	requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto com os dados do usuário a ser cadastrado", 
	required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)))
	@ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso", content = @Content(mediaType =  MediaType.TEXT_PLAIN_VALUE)),
    @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)),
    @ApiResponse(responseCode = "409", description = "Email já cadastrado", content = @Content(mediaType =  MediaType.TEXT_PLAIN_VALUE)),
    @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(mediaType =  MediaType.TEXT_PLAIN_VALUE))
	})
	public ResponseEntity<?> criarUsuario(@RequestBody @Valid UsuarioCadastroDTO usuario) {
		ResponseEntity<?> response;
		try {
			response = usuarioService.criarUsuario(usuario);
		}catch (Exception e){
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR");

		}
		return response;
	}
	

	@PutMapping("/api/usuarios")
	@Operation(summary = "Edita o cadastro do usuário")
	public ResponseEntity<?> EditarUsuario (@Valid @RequestBody UsuarioLogadoDTO usuario) {
		ResponseEntity<?> response;
		try{
			response = usuarioService.editarDadosUsuario(usuario);
		}catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR");
		}
		return response;

	}
	
	//Padrão é 204. no context
	@DeleteMapping("/api/usuarios/{id}")
	@Operation(summary = "Deleta usuário pelo ID",
    parameters = {
        @io.swagger.v3.oas.annotations.Parameter(name = "id", description = "ID do Usuário", required = true, example = "5")
    })
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso", content = @Content(mediaType =  MediaType.TEXT_PLAIN_VALUE)),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType =  MediaType.TEXT_PLAIN_VALUE)),
			@ApiResponse(responseCode = "400", description = "ID de Usuário inválido", content = @Content(mediaType =  MediaType.TEXT_PLAIN_VALUE)),
			@ApiResponse(responseCode = "500", description = "Usuário não cadastrado/INTERNAL SERVER ERRO", content = @Content(mediaType =  MediaType.TEXT_PLAIN_VALUE))
	})
	public ResponseEntity<?> excluirUsuario(@PathVariable Integer id) {
		ResponseEntity<?> response;
		try{
			response = usuarioService.deletarUsuario(id);
		}catch(Exception e){
			response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR");
		}
		return response;
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
