package br.com.gabrielbobrov.beyondwork.application.serice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielbobrov.beyondwork.domain.cliente.Cliente;
import br.com.gabrielbobrov.beyondwork.domain.cliente.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void saveCliente (Cliente cliente)  {
		clienteRepository.save(cliente);
	}

}
