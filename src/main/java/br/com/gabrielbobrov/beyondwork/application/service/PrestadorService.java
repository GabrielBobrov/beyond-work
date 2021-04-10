package br.com.gabrielbobrov.beyondwork.application.service;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielbobrov.beyondwork.domain.cliente.Cliente;
import br.com.gabrielbobrov.beyondwork.domain.cliente.ClienteRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.Prestador;
import br.com.gabrielbobrov.beyondwork.domain.prestador.PrestadorComparator;
import br.com.gabrielbobrov.beyondwork.domain.prestador.PrestadorRepository;
import br.com.gabrielbobrov.beyondwork.domain.prestador.SearchFilter;
import br.com.gabrielbobrov.beyondwork.domain.prestador.SearchFilter.SearchType;

@Service
public class PrestadorService {

	@Autowired
	private PrestadorRepository prestadorRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ImageService imageService;

	@Transactional
	public void savePrestador(Prestador prestador) {
		if (!validateEmail(prestador.getEmail(), prestador.getId())) {
			throw new ValidationException("Este e-mail já está cadastrado no sistema!");

		}
		if (prestador.getId() != null) {// alterando
			Prestador prestadorDB = prestadorRepository.findById(prestador.getId()).orElseThrow();
			prestador.setSenha(prestadorDB.getSenha());
		} // else {//craindo
			// cliente.encryptPassword();

		// }

		prestadorRepository.save(prestador);

	}

	private boolean validateEmail(String email, Integer id) {

		Prestador prestador = prestadorRepository.findByEmail(email);
		Cliente cliente = clienteRepository.findByEmail(email);

		if (prestador != null) {
			return false;
		}
		if (cliente != null) {
			if (id == null) {
				return false;
			}

			if (!cliente.getId().equals(id)) {
				return false;
			}
		}

		return true;
	}

	public List<Prestador> search(SearchFilter filter) {
		List<Prestador> prestadores;
		if (filter.getSearchType() == SearchType.Texto) {
			prestadores = prestadorRepository.findByNomeIgnoreCaseContaining(filter.getTexto());
		} else if (filter.getSearchType() == SearchType.Categoria) {
			prestadores = prestadorRepository.findByservicosOferecidos_Id(filter.getCategoriaId());

		} else {
			throw new IllegalStateException("O tipo de busca " + filter.getSearchType() + " não é suportado");
		}

		Iterator<Prestador> it = prestadores.iterator();// iterator aponta para objeto especifico da coleção, so foi
															// utilizado por poder remover
		while (it.hasNext()) {
			Prestador prestador = it.next();
			double taxaEntrega = prestador.getPrecoVisitaBase().doubleValue();
			if (filter.isEntregaGratis() && taxaEntrega > 0 || filter.isEntregaGratis() && taxaEntrega == 0) {
				it.remove();
			}

		}
		//TODO: spring security PrestadorComparator comparator = new PrestadorComparator(filter, SecurityUtils.loggedCliente().getCep());
		//prestadores.sort(comparator);// usa o comparator como base para ordenar a lista de restaurantes
		return prestadores;

	}

}
