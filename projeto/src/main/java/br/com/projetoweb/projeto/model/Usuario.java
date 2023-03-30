package br.com.projetoweb.projeto.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@NotBlank(message ="Campo nome vazio! essa mensagem veio do Back-END")
	@Size(min = 3, message ="O nome deve ter no mínimo 3 caracteres.  essa mensagem veio do Back-END!")
	@Column(name = "nome", length = 200, nullable = false)
	private String nome;
	
	@Email(message ="Email inválido.  essa mensagem veio do Back-END")
	@NotBlank(message ="Campo email vazio!  essa mensagem veio do Back-END")
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@NotBlank(message ="Campo telefone vazio! essa mensagem veio do Back-END!")
	@Column(name = "telefone", length = 15, nullable = false)
	private String telefone;
	
	@NotBlank(message ="Campo senha vazio!  essa mensagem veio do Back-END!")
	@Column(name = "senha", length = 100, nullable = false)
	private String senha;
	
	
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
