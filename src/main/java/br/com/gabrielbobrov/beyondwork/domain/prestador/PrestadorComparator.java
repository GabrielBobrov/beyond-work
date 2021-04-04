package br.com.gabrielbobrov.beyondwork.domain.prestador;

import java.util.Comparator;

import br.com.gabrielbobrov.beyondwork.domain.prestador.SearchFilter.Order;



public class PrestadorComparator implements Comparator<Prestador> {
	
	private SearchFilter filter;
	private String cep;

	public PrestadorComparator(SearchFilter filter, String cep) {
		
		this.filter = filter;
		this.cep = cep;
	}

	@Override
	public int compare(Prestador r1, Prestador r2) {
		int result;
		
		if(filter.getOrder() == Order.Taxa) {
			result = r1.getPrecoVisitaBase().compareTo(r2.getPrecoVisitaBase());
		}else if(filter.getOrder() == Order.Tempo) {
			result = r1.calcularTempoEntrega(cep).compareTo(r2.calcularTempoEntrega(cep));//compare to retorna 1, 0 ou -1
		}else {
			throw new IllegalStateException("O valor de ordenação" + filter.getOrder() +"não é valido" );
		}
		return filter.isAsc() ? result : -result;
		
	}

}
