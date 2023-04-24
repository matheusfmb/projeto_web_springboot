package br.com.projetoweb.projeto.dto;

public class UsuarioLogadoDTO {

    private Integer id;
    private String email;
    private String nome;
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

