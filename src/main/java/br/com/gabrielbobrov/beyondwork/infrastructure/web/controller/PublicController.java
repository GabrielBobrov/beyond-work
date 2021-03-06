package br.com.gabrielbobrov.beyondwork.infrastructure.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.gabrielbobrov.beyondwork.application.service.ClienteService;
import br.com.gabrielbobrov.beyondwork.application.service.PrestadorService;
import br.com.gabrielbobrov.beyondwork.domain.cliente.Cliente;
import br.com.gabrielbobrov.beyondwork.domain.prestador.CategoriaPrestadorRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.Prestador;

@Controller
@RequestMapping("/public")
public class PublicController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private PrestadorService prestadorService;

	@Autowired
	private CategoriaPrestadorRepository categoriaPrestadorRepository;

	@GetMapping("/login")
	public String login() {
		return "/login";
	}

	@GetMapping("/prestador/cadastro")
	public String pagePrestadorCadastro(@ModelAttribute("prestador") Prestador prestador, Model model) {

		ControllerHelper.addCategoriasToRequest(categoriaPrestadorRepository, model);
		return "/prestador-cadastro";
	}

	@PostMapping("/prestador/save")
	public String prestadorCadastro(@ModelAttribute("prestador") @Valid Prestador prestador, BindingResult result,
			Model model) {
		ControllerHelper.addCategoriasToRequest(categoriaPrestadorRepository, model);
		if (result.hasErrors()) {
			return "/prestador-cadastro";
		}
		ControllerHelper.addCategoriasToRequest(categoriaPrestadorRepository, model);
		prestadorService.savePrestador(prestador);
		model.addAttribute("msg", "Prestador Cadastrado!");

		return "/prestador-cadastro";
	}

	@GetMapping("/cliente/cadastro")
	public String pageClienteCadastro(@ModelAttribute("cliente") Cliente cliente, Model model) {
		return "/cliente-cadastro";
	}

	@PostMapping("/cliente/save")
	public String clienteCadastro(@ModelAttribute("cliente") @Valid Cliente cliente, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "/cliente-cadastro";
		}
		clienteService.saveCliente(cliente);
		model.addAttribute("msg", "Cliente Cadastrado!");

		return "/cliente-cadastro";
	}

}
