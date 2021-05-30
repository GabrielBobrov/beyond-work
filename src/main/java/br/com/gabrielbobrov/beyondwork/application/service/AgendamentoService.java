package br.com.gabrielbobrov.beyondwork.application.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento.Status;
import br.com.gabrielbobrov.beyondwork.domain.cliente.ClienteRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.PrestadorRepository;

@Service
public class AgendamentoService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	public Agendamento agendamento(String numCartao) {
		Agendamento agendamento = new Agendamento();
		agendamento.setStatus(Status.Aguardando);
		agendamento.setCliente(clienteRepository.findById(1).orElseThrow());
		agendamento.setData(LocalDateTime.now());
		agendamento.setSubtotal(null);
		agendamento.setPrestador(prestadorRepository.findById(1).orElseThrow());
		
		//TODO: continuar a fazer agendamento
		return agendamento;
		
	}
	
	
	
}
