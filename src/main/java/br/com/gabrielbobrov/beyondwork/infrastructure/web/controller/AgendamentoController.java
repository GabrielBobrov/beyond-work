package br.com.gabrielbobrov.beyondwork.infrastructure.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	

	
	
	@GetMapping("/adicionar")
	public String adicionarPrestador(@RequestParam("prestadorId") Integer prestadorId ,Model model) {
		Prestador prestador = prestadorRepository.findById(prestadorId).orElseThrow();
		model.addAttribute("prestador", prestador);
		return "/cliente-agendamento";
	}
	
	@GetMapping("/pagamento")
	public String viewPagamento(@RequestParam("prestadorId") Integer prestadorId ,Model model) {
		Prestador prestador = prestadorRepository.findById(prestadorId).orElseThrow();
		model.addAttribute("prestador", prestador);
		return "/cliente-agendamento-pagamento";
	}
	
	
	
	

}
