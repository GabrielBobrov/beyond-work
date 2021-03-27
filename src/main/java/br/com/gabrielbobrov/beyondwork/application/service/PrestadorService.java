package br.com.gabrielbobrov.beyondwork.application.service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielbobrov.beyondwork.domain.cliente.Cliente;
import br.com.gabrielbobrov.beyondwork.domain.cliente.ClienteRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.Prestador;
import br.com.gabrielbobrov.beyondwork.domain.prestador.PrestadorRepository;

@Service
public class PrestadorService {
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public void savePrestador (Prestador prestador)  {
		if(!validateEmail(prestador.getEmail(), prestador.getId())) {
			throw new ValidationException("Este e-mail já está cadastrado no sistema!");
			
		}
		if(prestador.getId() != null) {//alterando
			Prestador prestadorDB = prestadorRepository.findById(prestador.getId()).orElseThrow();
			prestador.setSenha(prestadorDB.getSenha());
		}//else {//craindo
			//cliente.encryptPassword();
			
		//}
	
		prestadorRepository.save(prestador);
		
	}
	
	
private boolean validateEmail(String email, Integer id) {
		
		Prestador prestador = prestadorRepository.findByEmail(email);
		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(prestador != null) {
			return false;
		}
		if (cliente != null) {
			if (id == null) {
				return false;
			}
			
			if(!cliente.getId().equals(id)) {
				return false;
			}
		}
		
		return true;
	}

}


