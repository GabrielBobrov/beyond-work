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
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PrestadorRepository prestadorRepository;
	
	@Transactional
	public void saveCliente (Cliente cliente)  {
		if(!validateEmail(cliente.getEmail(), cliente.getId())) {
			throw new ValidationException("Este e-mail já está cadastrado no sistema!");
			
		}
		if(cliente.getId() != null) {//alterando
			Cliente clienteDB = clienteRepository.findById(cliente.getId()).orElseThrow();
			cliente.setSenha(clienteDB.getSenha());
		}//else {//craindo
			//cliente.encryptPassword();
			
		//}
	
		clienteRepository.save(cliente);
		
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
