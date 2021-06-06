package br.com.gabrielbobrov.beyondwork.domain.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielbobrov.beyondwork.domain.prestador.Prestador;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer>{

}
