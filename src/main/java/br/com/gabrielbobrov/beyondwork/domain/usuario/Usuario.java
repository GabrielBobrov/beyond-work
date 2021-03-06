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
	
	@NotBlank
	@Size(max = 80,message = "Nome muito grande!")
	private String nome;
	
	@NotBlank(message = "e-mail não pode ser vazio")
	@Size(max = 60, message = "E-mail muito grande!")
	@Email(message = "E-mail invalido!")
	private String email;
	
	@NotBlank(message = "Senha não pode ser vazia!")
	@Size(max = 250, message = "Senha muito grande!")
	private String senha;
	
	@NotBlank(message = "Telefone não pode ser vazio")
	@Pattern(regexp = "[0-9]{10,11}", message= "Telefone possui formato inválido")
	@Size(max = 60, message = "Telefone muito grande")
	@Column(length = 11, nullable = false)
	private String telefone;
	
	//TODO: encrypt password

}
