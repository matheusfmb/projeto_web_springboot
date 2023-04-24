package br.com.projetoweb.projeto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import br.com.projetoweb.projeto.dto.UsuarioCadastroDTO;
import br.com.projetoweb.projeto.dto.UsuarioLoginDTO;
import br.com.projetoweb.projeto.model.Usuario;
import br.com.projetoweb.projeto.repository.UsuarioRepository;
import br.com.projetoweb.projeto.service.UsuarioService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired 
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testLoginUsuario() {
        String senha = "123";
        String senhaEncoded = passwordEncoder.encode(senha);
        UsuarioCadastroDTO usuarioDTO = new UsuarioCadastroDTO("felix","felix@gmail.com","(24)92342-2332",senhaEncoded);
        Usuario usuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getEmail(), usuarioDTO.getTelefone(), senhaEncoded);
        usuario = repository.save(usuario);

        UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO(usuario.getEmail(), senha);
        ResponseEntity<?> response = usuarioService.loginUsuario(usuarioLoginDTO);
        
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Authorization"));
        //TESTE - O MÉTODO NO SERVICE ESTÁ RETORNANDO UM OPTIONAL<Usuario>, MAS AK EU ESTAVA COMPARANDO COM O OBJETO USUÁRIO da classe USUÁRIO COM O OPTIONAL<Usuario>.
        //ENFIM. QUANDO VOCÊ ESTIVER LENDO ISSO NO FUTURO. PARE E PENSE A RESPEITO DE UM LÓGICA PRA ENCAIXAR O TESTE, ELE ESTÁ CERTO, O CONTEÚDO DO USUÁRIO ESTÁ VINDO CERTO,
        //MAS NÃO É O MESMO OBJETO PARA O ASSERTEQUALS.
        Optional<?> usuarioOptional = (Optional<?>) response.getBody();
        assertEquals(usuario, usuarioOptional);

        
        usuarioService.deletarUsuario(usuario.getId());
    }

}


