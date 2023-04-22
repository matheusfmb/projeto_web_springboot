package br.com.projetoweb.projeto.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


@Component
public class UsuarioLoginDTO {

    @Email(message ="Email inválido.")
    @NotBlank(message ="Campo email vazio!")
    private String email;

    @NotBlank(message ="Campo senha vazio!")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}


