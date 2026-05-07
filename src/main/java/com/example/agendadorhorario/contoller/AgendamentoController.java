package com.example.agendadorhorario.contoller;

import com.example.agendadorhorario.infrastruture.entity.AgendamentoEntity;
import com.example.agendadorhorario.infrastruture.repository.AgendamentoRepository;
import com.example.agendadorhorario.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamento")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Gerenciamento de usuários")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    @Operation(summary = "Criar usuários")
    public ResponseEntity<AgendamentoEntity> salvarAgendamento(@RequestBody AgendamentoEntity agendamentoEntity) {
        return ResponseEntity.accepted().body(agendamentoService.salvarAgendamento(agendamentoEntity));
    }

    @DeleteMapping
    @Operation(summary = "Deletar usuários")
    public ResponseEntity<Void> deletarAgendamento(@RequestParam String cliente, @RequestParam LocalDateTime dataHoraAgendamento) {
        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Listar usuários")
    public ResponseEntity <List<AgendamentoEntity>> buscarAgendamentoDoDia(@RequestParam LocalDate date) {
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentoDoDia(date));
    }

    @PutMapping
    @Operation(summary = "Editar usuários")
    public ResponseEntity<AgendamentoEntity> alterarAgendamento(@RequestBody AgendamentoEntity agendamentoEntity, @RequestParam String cliente, @RequestParam LocalDateTime dataHoraAgendamento){
        return ResponseEntity.accepted().body(agendamentoService.alterarAgendamentoDoDia(agendamentoEntity, cliente, dataHoraAgendamento));
    }
}
