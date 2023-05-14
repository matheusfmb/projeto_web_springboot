package br.com.projetoweb.projeto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UsuarioLogadoDTO {

    private Integer id;
    private String nome;
    
    @Email(message ="Email inválido.")
    @NotBlank(message ="Campo email vazio! Validação vinda do BACK-END")
    private String email;

    @NotBlank(message ="Campo telefone vazio! Validação vinda do BACK-END ")
    private String telefone;

    public UsuarioLogadoDTO(Integer id,String email, String nome, String telefone) {
        this.id = id;
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

