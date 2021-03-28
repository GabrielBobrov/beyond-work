package br.com.gabrielbobrov.beyondwork.infrastructure.web.controller;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;

import br.com.gabrielbobrov.beyondwork.domain.prestador.CategoriaPrestador;
import br.com.gabrielbobrov.beyondwork.domain.prestador.CategoriaPrestadorRepository;


public class ControllerHelper {
	
	public static void setEditMode(Model model, boolean isEdit) {//desing patterns
		
		model.addAttribute("editMode", isEdit);
		
	}
	
	public static void addCategoriasToRequest(CategoriaPrestadorRepository repository, Model model) {
		
		List<CategoriaPrestador> categorias= repository.findAll(Sort.by("nome"));
		model.addAttribute("categorias", categorias);
		
	}

}