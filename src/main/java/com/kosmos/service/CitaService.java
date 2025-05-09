package com.kosmos.service;

import com.kosmos.entity.Cita;
import com.kosmos.entity.Consultorio;
import com.kosmos.entity.Doctor;
import com.kosmos.repository.ICita;
import com.kosmos.repository.IDoctores;
import com.kosmos.repository.IConsultorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaService {
    
    @Autowired
    private ICita citaRepository;
    @Autowired
    private IDoctores doctores;
    @Autowired
    private IConsultorio consultorio;
    
    public Cita guardarCita(Cita cita) throws IllegalArgumentException {
        LocalDateTime horario = cita.getFechaHora();

        
        if (citaRepository.existsByConsultorioAndHorario(cita.getConsultorio(), horario)) {
            throw new IllegalArgumentException("Ya hay una cita en este consultorio a esa hora.");
        }

        
        if (citaRepository.existsByDoctorAndHorario(cita.getDoctor(), horario)) {
            throw new IllegalArgumentException("El doctor ya tiene una cita a esa hora.");
        }

        
        List<Cita> citasPaciente = citaRepository.findByNombrePacienteAndHorario(horario, cita.getNombrePaciente());
        for (Cita c : citasPaciente) {
            if (Duration.between(c.getHorario(), horario).toHours() < 2) {
                throw new IllegalArgumentException("El paciente ya tiene una cita en menos de 2 horas.");
            }
        }

        
        List<Cita> citasDoctor = citaRepository.findByDoctorAndHorarioBetween(
                cita.getDoctor(),
                horario.toLocalDate().atStartOfDay(),
                horario.toLocalDate().atTime(23, 59)
        );
        if (citasDoctor.size() >= 8) {
            throw new IllegalArgumentException("El doctor no puede tener más de 8 citas en el día.");
        }

        return citaRepository.save(cita);
    }
    
    public List<Cita> obtenerCitasPorDoctorYFecha(Doctor doctor, LocalDateTime start, LocalDateTime end) {
        return citaRepository.findByDoctorAndHorarioBetween(doctor, start, end);
    }

    public List<Cita> obtenerCitasPorConsultorioYFecha(Consultorio consultorio, LocalDateTime start, LocalDateTime end) {
        return citaRepository.findByConsultorioAndHorarioBetween(consultorio, start, end);
    }
    

}
