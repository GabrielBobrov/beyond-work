package br.com.gabrielbobrov.beyondwork.domain.pagamento;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pagamento implements Serializable {
	
	@Id
	private Integer id;
	
	@NotNull
	@OneToOne
	@MapsId
	private Agendamento pedido;
	
	@NotNull
	private LocalDateTime data;
	
	@Column(name = "num_cartao_final")
	@NotNull
	@Size(min = 4,max = 4)
	private String numCartaoFinal;
	
	@Enumerated(EnumType.STRING)//para gravar o nome no banco e não a posicao de acordo com o enum
	@Column(nullable = false, length = 10)
	private BandeiraCartao bandeiraCartao;
	
	public void definirNumeroEBandeira(String numCartao) {
		numCartaoFinal = numCartao.substring(12);//pega o numero de posicao 12 pra frente
		bandeiraCartao = getBandeira(numCartao);
	}
	
	private BandeiraCartao getBandeira(String numCartao) {
		if(numCartao.startsWith("0000")) {
			return BandeiraCartao.AMEX;
		}
		if(numCartao.startsWith("1111")) {
			return BandeiraCartao.ELO;
		}
		if(numCartao.startsWith("2222")) {
			return BandeiraCartao.MASTER;
		}
		return BandeiraCartao.VISA;
	}

	

}