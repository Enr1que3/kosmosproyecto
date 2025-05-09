package com.kosmos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.kosmos.entity.Paciente;

@Repository

public interface IPacionete extends CrudRepository<Paciente, Long> {

}
