package com.alianza.test.service.impl;

import com.alianza.test.exception.ResourceNotFoundException;
import com.alianza.test.model.entity.Cliente;
import com.alianza.test.model.repository.IClienteRepository;
import com.alianza.test.service.interfaz.IClienteService;
import com.alianza.test.service.interfaz.IGenericService;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Service
public class ClienteService extends BaseService<Cliente> implements IClienteService {
    @Autowired
    private IClienteRepository repository;

    @Override
    public Cliente create(Cliente cliente) {
        //cliente.setFechaCreacion(new Date());
        cliente.setSharedKey(cliente.getNombre().replace(" ",""));
        return repository.save(cliente);
    }

    @Override
    public void deleteById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("cliente", "id", Integer.toString(id));
        }
    }

    @Override
    public Iterable<Cliente> findAll() {
        return repository.findAll();
    }

    @Override
    public Cliente findById(int id) throws ResourceNotFoundException {
        Optional<Cliente> cliente = repository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new ResourceNotFoundException("cliente", "id", Integer.toString(id));
        }
    }

    @Override
    public Cliente update(Cliente cliente) throws ResourceNotFoundException {
        if (repository.findById(cliente.getId()).isPresent()) {
            return repository.save(cliente);
        }
        throw new ResourceNotFoundException("cliente", "id", Integer.toString(cliente.getId()));
    }
    
    public ResultSearchData<Cliente> search(PageablePrimitive pag, String parametro) {
        Pageable paging = PageRequest.of(pag.getPage(), pag.getSize(), pag.getSortOrder().equals("asc") ? Sort.by(pag.getSortBy()).ascending() : Sort.by(pag.getSortBy()).descending());
        Page<Cliente> pagedResult = repository.findAll(paging);
        return (ResultSearchData<Cliente>) this.getResultSearch(pagedResult);
    }
}
