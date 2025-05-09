package com.kosmos.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.kosmos.entity.Cita;
import com.kosmos.service.CitaService;
import com.kosmos.entity.Cita;
import com.kosmos.entity.Doctor;
import com.kosmos.entity.Consultorio;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import com.kosmos.repository.ICita;
import com.kosmos.repository.IDoctores;
import com.kosmos.repository.IConsultorio;
import org.springframework.web.bind.annotation.RequestMethod;
import com.kosmos.service.CitaService;

@RestController
@RequestMapping
public class VistaCita {

    
    @Autowired
    private CitaService citaService;
    
    @Autowired
    private IDoctores doctores;

    @Autowired
    private IConsultorio consultorioRepo;
    
    
    @GetMapping("/saludar")
    public String saludo(){
        return "hola";
    }
    
    @PostMapping("/agendar")
    public ResponseEntity<?> agendarCita(@RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.guardarCita(cita);
            return ResponseEntity.ok(nuevaCita);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorDoctor(
            @PathVariable Long doctorId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {

        Optional<Doctor> oDoctor = doctores.findById(doctorId);
        if (oDoctor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Doctor doctor = oDoctor.get();
        LocalDateTime start = LocalDateTime.parse(fechaInicio);
        LocalDateTime end = LocalDateTime.parse(fechaFin);
        List<Cita> citas = citaService.obtenerCitasPorDoctorYFecha(doctor, start, end);
        return ResponseEntity.ok(citas);
    }

    @GetMapping("/consultorio/{consultorioId}")
    public ResponseEntity<List<Cita>> obtenerCitasPorConsultorio(
            @PathVariable Long consultorioId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {

        Optional<Consultorio> oConsultorio = consultorioRepo.findById(consultorioId);
        if (oConsultorio.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Consultorio consultorio = oConsultorio.get();
        LocalDateTime start = LocalDateTime.parse(fechaInicio);
        LocalDateTime end = LocalDateTime.parse(fechaFin);
        List<Cita> citas = citaService.obtenerCitasPorConsultorioYFecha(consultorio, start, end);
        return ResponseEntity.ok(citas);
    }
}
