package com.alianza.test.service.interfaz;

import com.alianza.test.exception.ResourceNotFoundException;

public interface IGenericService<T>{
    T create(T T);

    void deleteById(int id) throws ResourceNotFoundException;

    Iterable<T> findAll();

    T findById(int id) throws ResourceNotFoundException;

    T update(T T) throws ResourceNotFoundException;
}