package com.example.agendadorhorario.services;

import com.example.agendadorhorario.infrastruture.entity.AgendamentoEntity;
import com.example.agendadorhorario.infrastruture.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoEntity salvarAgendamento(AgendamentoEntity agendamentoEntity) {

        LocalDateTime horaAgendamento = agendamentoEntity.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamentoEntity.getDataHoraAgendamento().plusMinutes(30);

        AgendamentoEntity agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamentoEntity.getServico(), horaAgendamento, horaFim);

        if(Objects.nonNull(agendados)) {
            throw new RuntimeException("Horário já está preenchido");
        }
        return agendamentoRepository.save(agendamentoEntity);
    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {
        agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    public List<AgendamentoEntity> buscarAgendamentoDoDia(LocalDate data) {
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(23, 59, 59);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia);
    }

    public AgendamentoEntity alterarAgendamentoDoDia(AgendamentoEntity agendamentoEntity, String cliente, LocalDateTime dataHoraAgendamento) {
        AgendamentoEntity agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

        if(Objects.isNull(agenda)) {
            throw new RuntimeException("Horário não está preenchido");
        }

        agendamentoEntity.setId(agenda.getId());
        return agendamentoRepository.save(agendamentoEntity);
    }
}
