package br.com.gabrielbobrov.beyondwork.domain.Agendamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.gabrielbobrov.beyondwork.domain.cliente.Cliente;
import br.com.gabrielbobrov.beyondwork.domain.pagamento.Pagamento;
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
		Concluido(3,"Serviço Conlcuido", true);
		
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
	
	@NotNull
	private LocalDateTime data;
	
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@NotNull
	private Status status;
	
	@NotNull
	@ManyToOne
	private Prestador prestador;
	
	@NotNull
	private BigDecimal subtotal;
	
	
	
	@NotNull
	private BigDecimal total;
	
	
	
	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;
	
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
	
}