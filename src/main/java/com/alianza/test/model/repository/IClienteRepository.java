package com.alianza.test.model.repository;

import com.alianza.test.model.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends CrudRepository<Cliente,Integer> {
    Page<Cliente> findAll(Pageable paging);
}