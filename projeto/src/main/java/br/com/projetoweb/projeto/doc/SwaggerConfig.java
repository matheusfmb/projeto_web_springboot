package br.com.projetoweb.projeto.doc;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Projeto API",
        version = "1.0",
        description = "Documentação da minha API feita para estudo"
    )
)
public class SwaggerConfig {
	
}
