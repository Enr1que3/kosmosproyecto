package com.kosmos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.kosmos.entity.Cita;
import com.kosmos.entity.Doctor;
import com.kosmos.entity.Consultorio;
import com.kosmos.entity.Paciente;
import java.util.List;
import java.time.LocalDateTime;


@Repository
public interface ICita extends CrudRepository<Cita, Long> {
    
    List<Cita> findByDoctorAndFechaHoraBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
    List<Cita> findByConsultorioAndFechaHoraBetween(Consultorio consultorio, LocalDateTime start, LocalDateTime end);
    List<Cita> findByPacienteAndFechaHoraBetween(Paciente paciente, LocalDateTime start, LocalDateTime end);
    boolean existsByDoctorAndFechaHora(Doctor doctor, LocalDateTime fechaHora);
    boolean existsByConsultorioAndFechaHora(Consultorio consultorio, LocalDateTime fechaHora);
}
