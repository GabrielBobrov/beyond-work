package br.com.gabrielbobrov.beyondwork.domain.agendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.gabrielbobrov.beyondwork.domain.cliente.Cliente;
import br.com.gabrielbobrov.beyondwork.domain.prestador.Prestador;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "agendamento")
public class Agendamento {
	public enum Status{
		Aguardando(1,"Aguardando Confirmação", false),
		Confirmado(2,"Confirmado", false),
		Concluido(3,"Serviço Conlcuido", false),
		Recusado(4,"Serviço recusado", true);
		
		Status(int ordem, String descricao, boolean ultimo) {
			this.ordem = ordem;
			this.descricao=descricao;
			this.ultimo=ultimo;
		}
		int ordem;
		String descricao;
		boolean ultimo;
		public String getDescricao() {
			return descricao;
		}
		
		public int getOrdem() {
			return ordem;
		}
		public boolean isUltimo() {
			return ultimo;
		}
		public static Status fromOrdem(int ordem) {
			for(Status status : Status.values()) {
				if(status.getOrdem() == ordem) {
					return status;
				}
			}
			return null;
		}
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime data;
	
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@NotNull
	@ManyToOne
	private Prestador prestador;
	
	@NotNull
	private BigDecimal subtotal;
	
	
	
	@NotNull
	private BigDecimal total;
	
	@NotNull
	private String pagamento;
	
	@NotNull
	private String cepDestino;
	
	@NotNull
	private String info;
	
//	@OneToOne(mappedBy = "pedido")
//	private Pagamento pagamento;
	
	public String getFormattedId() {
		return String.format("#%04d", id);
	}
	
	public void definirProximoStatus() {
		int ordem = status.getOrdem();
		Status newStatus = Status.fromOrdem(ordem + 1);
		
		if(newStatus != null) {
			this.status = newStatus;
		}
	}

	public void confirmAgendamento() {
		Status newStatus = Status.Confirmado;
		if(newStatus != null) {
			this.status = newStatus;
		}
	}
	
	public void recuseAgendamento() {
		Status newStatus = Status.Recusado;
		if(newStatus != null) {
			this.status = newStatus;
		}
	}
	
	public void finishAgendamento() {
		Status newStatus = Status.Concluido;
		if(newStatus != null) {
			this.status = newStatus;
		}
	}
	
}
