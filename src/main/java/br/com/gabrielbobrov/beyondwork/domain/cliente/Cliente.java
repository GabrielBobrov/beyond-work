package br.com.gabrielbobrov.beyondwork.domain.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.br.CPF;

import br.com.gabrielbobrov.beyondwork.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@SuppressWarnings("serial")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Cliente extends Usuario{
	
	
	@NotBlank(message = "O CPF não pode ser vazio")
	@Pattern(regexp = "[0-9]{11}", message= "O CPF possui formato inválido")
	@Column(length = 11, nullable = false)
	@CPF(message = "Este CPF não é verdadeiro")
	private String cpf;
	
	@NotBlank(message = "O CEP não pode ser vazio")
	@Pattern(regexp = "[0-9]{8}", message= "O CEP possui formato inválido")
	@Column(length = 8)
	private String cep;
	
	public String getFormattedCep() {
		
		return cep.substring(0,5) + "-" + cep.substring(5);
		
	}
}
