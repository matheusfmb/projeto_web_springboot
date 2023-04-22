package br.com.projetoweb.projeto.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
	@Column(name = "senha", length = 100, nullable = false)
	private String senha;
	
	
	public Usuario(String nome, String email, String telefone, String senha) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
	}

	public Usuario() {
		
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
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", senha="
				+ senha + "]";
	}
	
}
