package br.com.gabrielbobrov.beyondwork.domain.prestador;

import br.com.gabrielbobrov.beyondwork.util.StringUtils;
import lombok.Data;

@Data
public class SearchFilter {

	public enum SearchType {
		Texto, Categoria
	}

	public enum Order {
		Taxa, Tempo;
	}

	public enum Command {
		 MaiorPrecoServico, MenorPrecoServico, MaiorTempo, MenorTempo;
	}

	private String texto;
	private SearchType searchType;
	private Integer categoriaId;
	private Order order = Order.Taxa;
	private boolean asc;
	private boolean entregaGratis;

	public void processFilter(String cmdString) {
		if (!StringUtils.isEmpty(cmdString)) {
			Command cmd = Command.valueOf(cmdString);
			 if (cmd == Command.MaiorTempo) {
				order = Order.Taxa;
				asc = false;
			}else if (cmd == Command.MenorPrecoServico) {
				order = Order.Taxa;
				asc = true;
			}
			else if (cmd == Command.MaiorTempo) {
				order = Order.Tempo;
				asc = false;
			}else if (cmd == Command.MenorTempo) {
				order = Order.Tempo;
				asc = true;
			}
		}
		if (searchType == SearchType.Texto) {
			categoriaId = null;
		} else if (searchType == SearchType.Categoria) {
			texto = null;
		}

	}

}
