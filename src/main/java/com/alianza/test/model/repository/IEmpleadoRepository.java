package com.alianza.test.model.repository;

import com.alianza.test.model.entity.Empleado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IEmpleadoRepository extends CrudRepository<Empleado,Integer> {
    Page<Empleado> findAll(Pageable paging);
    Page<Empleado> findAllByNombreContains(Pageable paging, String param);
}