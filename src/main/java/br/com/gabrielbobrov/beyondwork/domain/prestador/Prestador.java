package br.com.gabrielbobrov.beyondwork.domain.prestador;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.gabrielbobrov.beyondwork.domain.usuario.Usuario;
import br.com.gabrielbobrov.beyondwork.infrastructure.web.validator.UploadConstraint;
import br.com.gabrielbobrov.beyondwork.util.FileType;
import br.com.gabrielbobrov.beyondwork.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Table(name = "prestador")
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Prestador extends Usuario {
	
	@NotBlank
	@Pattern(regexp = "[0-9]{11}", message= "O CPF possui formato inválido")
	@Column
	private String cpf;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "Arquivo precisa estar em formato PNG" )
	private transient MultipartFile fotoFile;
	
	@NotNull(message = "O preço da visita deve ser informado!")
	@Min(0)
	@Max(200)
	private BigDecimal precoVisitaBase;
	
	@NotNull(message = "O tempo médio para chegar no cliente deve ser informado!")
	@Min(0)
	@Max(200)
	private Integer tempoChegarVista;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "prestador_has_categorias",
			joinColumns = @JoinColumn(name="prestador_id"),
			inverseJoinColumns = @JoinColumn(name="categoira_prestador_id")
			)
	@Size(min = 1,message = "O prestador precisa oferecer pelo menos 1 serviço")
	@ToString.Exclude
	private Set<CategoriaPrestador> servicosOferecidos = new HashSet<>(0);
	
	private Double taxtaFixa = 20.00;
	
	public BigDecimal getPrecoTotal() {
		BigDecimal soma =  BigDecimal.ZERO;
		
		soma = soma.add(precoVisitaBase);
		soma = soma.add(BigDecimal.valueOf(taxtaFixa));
		
		
		
		
		return soma;
	}
	
	
	public Integer calcularTempoEntrega(String cep) {
		int soma = 0;
		for( char c : cep.toCharArray()) {
			int v =Character.getNumericValue(c);//getnumericvalue converte string em int
			if(v>0) {
				soma+=v;
				
			}
			
		}
		soma/=2;
		return tempoChegarVista + soma;
	}
	public String getCategoriaAsText() {
		Set<String> strings = new LinkedHashSet<>(); //linkedhashset não permite elementos duplicados
		
		for(CategoriaPrestador categoria : servicosOferecidos) {
			strings.add(categoria.getNome());
		}
		
		return StringUtils.concatenate(strings);
	}
}
