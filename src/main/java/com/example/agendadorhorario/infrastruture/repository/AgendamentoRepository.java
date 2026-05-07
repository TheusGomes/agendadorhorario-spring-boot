package com.example.agendadorhorario.infrastruture.repository;

import com.example.agendadorhorario.infrastruture.entity.AgendamentoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public interface AgendamentoRepository extends JpaRepository<AgendamentoEntity, Long> {

    AgendamentoEntity findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio, LocalDateTime dataFinal);

    @Transactional
    void deleteByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

    List<AgendamentoEntity> findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFinal);

    AgendamentoEntity findByDataHoraAgendamentoAndCliente(LocalDateTime dataHoraAgendamento, String cliente);

}
