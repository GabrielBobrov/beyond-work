package br.com.gabrielbobrov.beyondwork.domain.agendamento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{
	
	public Agendamento findByIdAndPrestador_Id(Integer pedidoId, Integer prestadorId);
	
	@Query("SELECT a FROM Agendamento a WHERE a.prestador.id = ?1 AND a.data BETWEEN ?2 AND ?3")
	public List<Agendamento> findByDateInterval(Integer restauranteId, LocalDateTime dataInicial, LocalDateTime dataFinal);

}
