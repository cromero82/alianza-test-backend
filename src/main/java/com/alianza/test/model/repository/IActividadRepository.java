package com.alianza.test.model.repository;

import com.alianza.test.model.entity.Actividad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IActividadRepository extends CrudRepository<Actividad,Integer> {
    Page<Actividad> findAll(Pageable paging);
    Page<Actividad> findAllByNombreContains(Pageable paging, String param);
}