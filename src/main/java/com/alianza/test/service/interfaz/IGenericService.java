package com.alianza.test.service.interfaz;

import com.alianza.test.exception.InternalServerException;
import com.alianza.test.exception.ResourceNotFoundException;

public interface IGenericService<T>{
    T create(T T) throws InternalServerException;

    void deleteById(int id) throws ResourceNotFoundException;

    Iterable<T> findAll();

    T findById(int id) throws ResourceNotFoundException;

    T update(T T) throws ResourceNotFoundException;
}