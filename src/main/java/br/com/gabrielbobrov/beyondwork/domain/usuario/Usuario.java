package br.com.gabrielbobrov.beyondwork.domain.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario implements Serializable{
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Digite um nome")
	@Size(max = 80,message = "Nome muito grande, abrevie algum sobrenome")
	private String nome;
	
	@NotBlank(message = "e-mail não pode ser vazio")
	@Size(max = 60, message = "E-mail muito grande")
	@Email(message = "E-mail invalido!")
	private String email;
	
	@NotBlank(message = "Senha não pode ser vazia")
	@Size(max = 250, message = "Senha muito grande")
	private String senha;
	
	@NotBlank(message = "Senha não pode ser vazia")
	@Column(insertable = false,updatable = false)
	@Size(max = 250, message = "Senha muito grande")
	private String contraSenha;
	
	@NotBlank(message = "Telefone não pode ser vazio")
	@Pattern(regexp = "[0-9]{10,11}", message= "Telefone possui formato inválido")
	@Size(max = 60, message = "Telefone muito grande")
	@Column(length = 11, nullable = false)
	private String telefone;
	
	@NotBlank(message = "O CEP não pode ser vazio")
	@Pattern(regexp = "[0-9]{8}", message= "O CEP possui formato inválido")
	@Column(length = 8)
	private String cep;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String rua;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String uf;
	
	@NotBlank(message = "Digite o complemento")
	private String complemento;
	 
	
public String getFormattedCep() {
		
		return cep.substring(0,5) + "-" + cep.substring(5);
		
	}
	
	//TODO: encrypt password

}
