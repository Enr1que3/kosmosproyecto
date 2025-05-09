package com.kosmos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import java.time.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;



@Entity
@Table(name = "citas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_doctor")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "id_consultorio")
    private Consultorio consultorio;

    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;

    private LocalDateTime fechaHora;

    
}
