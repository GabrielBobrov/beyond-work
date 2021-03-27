package br.com.gabrielbobrov.beyondwork.domain.prestador;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadorRepository extends JpaRepository<Prestador, Integer> {

	public Prestador findByEmail(String email);

}
