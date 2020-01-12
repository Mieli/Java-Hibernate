package model;

import java.math.BigInteger;
import java.security.MessageDigest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity

@NamedQueries({
	
	@NamedQuery(name = "Usuario.findAll", query= "SELECT u FROM Usuario u"),
	@NamedQuery(name = "Usuario.findByName", query = "SELECT u FROM Usuario u WHERE u.nome=:nome")
})


public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	private String email;
	
	private String login;
	
	private String senha;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			
			MessageDigest message = MessageDigest.getInstance("MD5");
			message.update(senha.getBytes(), 0, senha.length());
			
			String senhaCriptograda = new BigInteger(1, message.digest()).toString(16);
			
			this.senha = senhaCriptograda;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", login=" + login + ", senha=" + senha
				+ "]";
	}
	
	
	

}
