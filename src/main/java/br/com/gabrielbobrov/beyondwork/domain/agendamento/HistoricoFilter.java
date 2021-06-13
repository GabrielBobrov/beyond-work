package br.com.gabrielbobrov.beyondwork.domain.agendamento;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class HistoricoFilter {

	private Integer pedidoId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInicial;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFinal;

}
