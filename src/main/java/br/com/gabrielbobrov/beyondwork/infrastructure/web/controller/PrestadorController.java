package br.com.gabrielbobrov.beyondwork.infrastructure.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.gabrielbobrov.beyondwork.application.service.ClienteService;
import br.com.gabrielbobrov.beyondwork.application.service.HistoricoService;
import br.com.gabrielbobrov.beyondwork.application.service.PrestadorService;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento.Status;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.AgendamentoRepository;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.HistoricoFilter;
import br.com.gabrielbobrov.beyondwork.domain.cliente.Cliente;
import br.com.gabrielbobrov.beyondwork.domain.cliente.ClienteRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.CategoriaPrestador;
import br.com.gabrielbobrov.beyondwork.domain.prestador.CategoriaPrestadorRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.Prestador;
import br.com.gabrielbobrov.beyondwork.domain.prestador.PrestadorRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.SearchFilter;

@Controller
@RequestMapping("/prestador")
public class PrestadorController {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	@Autowired
	private PrestadorService prestadorService;
	
	@Autowired
	private HistoricoService historicoService;
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private CategoriaPrestadorRepository categoriaPrestadorRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Agendamento> agendamentos = agendamentoRepository.findByStatus(Status.Aguardando);
		model.addAttribute("agendamentos",agendamentos);
		return "/prestador-pendentes";
	}
	
	
	@GetMapping("/historico")
	public String viewHistorico(@ModelAttribute("historicoFilter") HistoricoFilter filter, Model model) {
		Prestador prestadorId = prestadorRepository.findById(1).orElseThrow();
		
		List<Agendamento> agendamentos = historicoService.listPedidos(prestadorId.getId(), filter);
		model.addAttribute("agendamentos",agendamentos);
		model.addAttribute("filter", filter);
		return "/prestador-historico";
	}
	
	@GetMapping("/agendamentos/pendentes")
	public String viewPedidosPendentes(Model model) {
		List<Agendamento> agendamentos = agendamentoRepository.findByStatus(Status.Aguardando);
		model.addAttribute("agendamentos",agendamentos);
		return "/prestador-pendentes";
	}
	
	@PostMapping("/agendamento/confirmar")
	public String confirmAgendamento(@RequestParam("agendamentoId") Integer agendamentoId, Model model) {
		Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElseThrow();
		agendamento.confirmAgendamento();
		agendamentoRepository.save(agendamento);
		
		model.addAttribute("agendamento", agendamento);
		viewPedidosConfirmados(model);
		return "/prestador-confirmados";
	}
	
	@PostMapping("/agendamento/recusar")
	public String recuseAgendamento(@RequestParam("agendamentoId") Integer agendamentoId, Model model) {
		Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElseThrow();
		agendamento.recuseAgendamento();
		agendamentoRepository.save(agendamento);
		
		model.addAttribute("agendamento", agendamento);
		viewPedidosPendentes(model);
		return "/prestador-pendentes";
	}
	
	@PostMapping("/agendamento/finalizar")
	public String finishAgendamento(@RequestParam("agendamentoId") Integer agendamentoId, Model model) {
		Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElseThrow();
		agendamento.finishAgendamento();
		agendamentoRepository.save(agendamento);
		
		model.addAttribute("agendamento", agendamento);
		viewPedidosPendentes(model);
		return "/prestador-pendentes";
	}
	
	@GetMapping("/agendamentos/executados")
	public String viewPedidosexecutados(Model model) {
		List<Agendamento> agendamentos = agendamentoRepository.findByStatus(Status.Concluido);
		model.addAttribute("agendamentos",agendamentos);
		return "/prestador-executados";
	}
	
	@GetMapping("/agendamentos/confirmados")
	public String viewPedidosConfirmados(Model model) {
		List<Agendamento> agendamentos = agendamentoRepository.findByStatus(Status.Confirmado);
		model.addAttribute("agendamentos",agendamentos);
		return "/prestador-confirmados";
	}
	
	
	
	

}
