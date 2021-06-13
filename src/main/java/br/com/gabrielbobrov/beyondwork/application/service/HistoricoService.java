package br.com.gabrielbobrov.beyondwork.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.AgendamentoRepository;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.HistoricoFilter;

@Service
public class HistoricoService {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
public List<Agendamento> listPedidos(Integer prestadorId, HistoricoFilter filter){
		
		Integer pedidoId = filter.getPedidoId();
		
		if(pedidoId != null) {
			Agendamento agendamento = agendamentoRepository.findByIdAndPrestador_Id(pedidoId, prestadorId);
			return List.of(agendamento);
		}
		LocalDate dataInicial = filter.getDataInicial();
		LocalDate dataFinal = filter.getDataFinal();
		
		if(dataInicial == null) {
			return List.of();//se data inicial for vazia retorna lista vazia
		}
		
		if (dataFinal == null) {
			dataFinal = LocalDate.now();
		}
		
		return agendamentoRepository.findByDateInterval(prestadorId, dataInicial.atStartOfDay(), dataFinal.atTime(23, 59, 59));
	
	}

}
