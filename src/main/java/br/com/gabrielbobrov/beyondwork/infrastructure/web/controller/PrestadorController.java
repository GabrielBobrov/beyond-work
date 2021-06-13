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

import br.com.gabrielbobrov.beyondwork.application.service.ClienteService;
import br.com.gabrielbobrov.beyondwork.application.service.HistoricoService;
import br.com.gabrielbobrov.beyondwork.application.service.PrestadorService;
import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento;
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
	private CategoriaPrestadorRepository categoriaPrestadorRepository;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<CategoriaPrestador> categorias = categoriaPrestadorRepository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);
		model.addAttribute("searchFilter", new SearchFilter());
		//implementar segurança para fazer pedidos by cliente
		return "/cliente-home";
	}
	@PostMapping("/save")
	public String cadastro( @ModelAttribute("cliente") @Valid Cliente cliente, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "/cliente-cadastro";
		}
		clienteService.saveCliente(cliente);
		
		return "/cliente-cadastro";
	}
	@GetMapping("/search")
	public String search(@ModelAttribute ("searchFilter") SearchFilter filter, Model model) {
		//filter.processFilter(cmdString);
		List<Prestador> prestadores = prestadorService.search(filter);
		ControllerHelper.addCategoriasToRequest(categoriaPrestadorRepository, model);
		model.addAttribute("prestadores",prestadores);
		model.addAttribute("searchFilter",filter);
		return "/cliente-search";
	}
	
	@GetMapping("/agendamento")
	public String viewAgendamento(Model model) {
		List<CategoriaPrestador> categorias = categoriaPrestadorRepository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);
		return "/cliente-agendamento";
	}
	
	@GetMapping("/pedido/")
	public String viewPedidos(Model model) {
		List<CategoriaPrestador> categorias = categoriaPrestadorRepository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);
		return "/cliente-pedido";
	}
	
	@GetMapping("/historico")
	public String viewHistorico(@ModelAttribute("historicoFilter") HistoricoFilter filter, Model model) {
		Prestador prestadorId = prestadorRepository.findById(1).orElseThrow();
		
		List<Agendamento> agendamentos = historicoService.listPedidos(prestadorId.getId(), filter);
		model.addAttribute("agendamentos",agendamentos);
		model.addAttribute("filter", filter);
		return "/prestador-historico";
	}
	
	

}
