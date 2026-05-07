package com.example.agendadorhorario.infrastruture.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "agendamento")
public class AgendamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String servico;

    @Column
    private String profissional;

    @Column
    private LocalDateTime dataHoraAgendamento;

    @Column
    private String cliente;

    @Column
    private String telefoneCliente;

    @Column
    private LocalDate dataInsercao = LocalDate.now();
}
