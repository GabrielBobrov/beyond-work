package br.com.gabrielbobrov.beyondwork.domain.prestador;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

	public Prestador findByEmail(String email);

	public List<Prestador> findByNomeIgnoreCaseContaining(String nome);

	public List<Prestador> findByservicosOferecidos_Id(Integer categoriaId);

}
