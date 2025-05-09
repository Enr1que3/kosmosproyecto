package com.kosmos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.kosmos.entity.Doctor;

@Repository
public interface IDoctores extends CrudRepository<Doctor, Long> {

}
