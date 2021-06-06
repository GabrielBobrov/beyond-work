package br.com.gabrielbobrov.beyondwork.infrastructure.web.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gabrielbobrov.beyondwork.application.service.AgendamentoService;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.AgendamentoRepository;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento.Status;
import br.com.gabrielbobrov.beyondwork.domain.cliente.Cliente;
import br.com.gabrielbobrov.beyondwork.domain.cliente.ClienteRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.CategoriaPrestadorRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.Prestador;
import br.com.gabrielbobrov.beyondwork.domain.prestador.PrestadorRepository;

@Controller
@RequestMapping("/cliente/agendamento")
public class AgendamentoController {
	
	
	@Autowired
	private CategoriaPrestadorRepository categoriaPrestadorRepository;
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private AgendamentoService agendamentoService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	

	
	
	@GetMapping
	public String adicionarPrestador(@ModelAttribute("agendamento")  Agendamento agendamento,@RequestParam("prestadorId") Integer prestadorId ,Model model) {
		Prestador prestador = prestadorRepository.findById(prestadorId).orElseThrow();
		model.addAttribute("prestador", prestador);
		model.addAttribute("agendamento", new Agendamento());
		return "/cliente-agendamento";
	}
	
	@PostMapping("/pagar")
	public String viewPagamento(@ModelAttribute("agendamento") @Valid Agendamento agendamento,BindingResult result, @RequestParam("prestadorId") Integer prestadorId ,Model model) {
//		if(result.hasErrors()) {
//			return "/cliente-agendamento";
//		}
		Prestador prestador = prestadorRepository.findById(prestadorId).orElseThrow();
		model.addAttribute("prestador", prestador);
		agendamento.setStatus(Status.Aguardando);
		System.out.println(agendamento.getData());
		agendamento.setPrestador(prestador);
		agendamento.setSubtotal(prestador.getPrecoVisitaBase());
		agendamento.setTotal(prestador.getPrecoTotal());
		agendamento.setCliente(clienteRepository.findById(1).orElseThrow());
		agendamentoRepository.save(agendamento);
		return "/cliente-agendamento";
	}
	
	
	
	

}
