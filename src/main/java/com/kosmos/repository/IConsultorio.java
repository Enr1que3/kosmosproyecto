
package com.kosmos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.kosmos.entity.Consultorio;

@Repository
public interface IConsultorio extends CrudRepository<Consultorio, Long> {

}
