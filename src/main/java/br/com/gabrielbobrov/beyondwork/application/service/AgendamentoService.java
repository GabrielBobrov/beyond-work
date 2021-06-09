package br.com.gabrielbobrov.beyondwork.application.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.AgendamentoRepository;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento.Status;
import br.com.gabrielbobrov.beyondwork.domain.cliente.ClienteRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.PrestadorRepository;

@Service
public class AgendamentoService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	public void doAgendamento(Agendamento agendamento) {
		agendamento.setStatus(Status.Aguardando);
		agendamento.setCliente(clienteRepository.findById(1).orElseThrow());
		
		//TODO: continuar a fazer agendamento
		
		
	}
	
	
	
}
