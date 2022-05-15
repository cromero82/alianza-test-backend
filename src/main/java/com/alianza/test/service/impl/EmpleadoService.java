package com.alianza.test.service.impl;

import com.alianza.test.exception.InternalServerException;
import com.alianza.test.exception.ResourceNotFoundException;
import com.alianza.test.model.entity.Empleado;
import com.alianza.test.model.repository.IClienteRepository;
import com.alianza.test.service.interfaz.IClienteService;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClienteService extends BaseService<Empleado> implements IClienteService {
    @Autowired
    private IClienteRepository repository;

    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Value( "${logging.custom.title}" )
    private String loginTitle;

    @Override
    public Empleado create(Empleado empleado) throws InternalServerException {
        try {
            return repository.save(empleado);
        }catch (Exception ex){
            logger.error(loginTitle + " Error creando empleado > " + empleado.toString());
            throw new InternalServerException("Registrando empleado: " + empleado.getNombre() ,ex);
        }
    }

    @Override
    public void deleteById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            logger.error(loginTitle + " Error eliminando empleado > id: " + id);
            throw new ResourceNotFoundException("empleado > id: " + id);
        }
    }

    @Override
    public Iterable<Empleado> findAll() {
        return repository.findAll();
    }

    @Override
    public Empleado findById(int id) throws ResourceNotFoundException {
        Optional<Empleado> empleado = repository.findById(id);
        if (empleado.isPresent()) {
            return empleado.get();
        } else {
            logger.warn(loginTitle + "Advertencia consultando > id: " + id);
            throw new ResourceNotFoundException("empleado", "id", Integer.toString(id));
        }
    }

    @Override
    public Empleado update(Empleado empleado) throws ResourceNotFoundException {
        if (repository.findById(empleado.getId()).isPresent()) {
            return repository.save(empleado);
        }
        logger.error(loginTitle + "Error actualizando empleado (no encontrado) > id = " + empleado.getId());
        throw new ResourceNotFoundException("empleado", "id", Integer.toString(empleado.getId()));
    }
    
    public ResultSearchData<Empleado> search(PageablePrimitive pag, String parametro) {
        Pageable paging = PageRequest.of(pag.getPage(), pag.getSize(), pag.getSortOrder().equals("asc") ? Sort.by(pag.getSortBy()).ascending() : Sort.by(pag.getSortBy()).descending());
        Page<Empleado> pagedResult = repository.findAllByNombreContains(paging, parametro);
        return (ResultSearchData<Empleado>) this.getResultSearch(pagedResult);
    }
}
