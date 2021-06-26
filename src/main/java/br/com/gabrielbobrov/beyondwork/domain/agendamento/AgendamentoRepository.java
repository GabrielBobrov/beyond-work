package br.com.gabrielbobrov.beyondwork.domain.agendamento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gabrielbobrov.beyondwork.domain.agendamento.Agendamento.Status;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{
	
	public Agendamento findByIdAndPrestador_IdAndStatus(Integer pedidoId, Integer prestadorId, Status status);
	
	
	@Query("SELECT a FROM Agendamento a WHERE a.prestador.id = ?1 AND a.data BETWEEN ?2 AND ?3 AND a.status = ?4")
	public List<Agendamento> findByDateInterval(Integer prestadorId, LocalDateTime dataInicial, LocalDateTime dataFinal, Status status);
	
	public List<Agendamento>  findByStatus(Status status);

}
