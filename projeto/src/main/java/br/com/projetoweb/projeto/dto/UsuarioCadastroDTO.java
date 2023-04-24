package br.com.projetoweb.projeto.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Component
public class UsuarioCadastroDTO {
    
    @NotBlank(message ="Campo nome vazio! Validação vinda do BACK-END")
    @Size(min = 3, message ="O nome deve ter no mínimo 3 caracteres.")
    private String nome;

    @Email(message ="Email inválido.")
    @NotBlank(message ="Campo email vazio! Validação vinda do BACK-END")
    private String email;

    @NotBlank(message ="Campo telefone vazio! Validação vinda do BACK-END ")
    private String telefone;

    @NotBlank(message ="Campo senha vazio! Validação vinda do BACK-END")
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UsuarioCadastroDTO(
            @NotBlank(message = "Campo nome vazio! Validação vinda do BACK-END") @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.") String nome,
            @Email(message = "Email inválido.") @NotBlank(message = "Campo email vazio! Validação vinda do BACK-END") String email,
            @NotBlank(message = "Campo telefone vazio! Validação vinda do BACK-END ") String telefone,
            @NotBlank(message = "Campo senha vazio! Validação vinda do BACK-END") String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
    }

    public UsuarioCadastroDTO() {
    }


    
}
