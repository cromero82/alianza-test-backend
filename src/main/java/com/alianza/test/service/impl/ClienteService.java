package com.alianza.test.service.impl;

import com.alianza.test.exception.InternalServerException;
import com.alianza.test.exception.ResourceNotFoundException;
import com.alianza.test.model.entity.Cliente;
import com.alianza.test.model.repository.IClienteRepository;
import com.alianza.test.service.interfaz.IClienteService;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ClienteService extends BaseService<Cliente> implements IClienteService {
    @Autowired
    private IClienteRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);


    @Value( "${logging.custom.title}" )
    private String loginTitle;

    @Override
    public Cliente create(Cliente cliente) throws InternalServerException {
        logger.trace("A TRACE Message");
        logger.debug("A DEBUG Message");
        logger.info("An INFO Message");
        logger.warn("A WARN Message");
        logger.error("An ERROR Message");

        cliente.setSharedKey(cliente.getNombre().replace(" ",""));
        try {
            return repository.save(cliente);
        }catch (Exception ex){
            logger.error(loginTitle + " Error creando cliente > " + cliente.toString());
            throw new InternalServerException("Registrando cliente: " + cliente.getSharedKey() ,ex);
        }
    }

    @Override
    public void deleteById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            logger.error(loginTitle + " Error eliminando cliente > id: " + id);
            throw new ResourceNotFoundException("cliente > id: " + id);
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
            logger.warn(loginTitle + "Advertencia consultando > id: " + id);
            throw new ResourceNotFoundException("cliente", "id", Integer.toString(id));
        }
    }

    @Override
    public Cliente update(Cliente cliente) throws ResourceNotFoundException {
        if (repository.findById(cliente.getId()).isPresent()) {
            return repository.save(cliente);
        }
        logger.error(loginTitle + "Error actualizando cliente (no encontrado) > id = " + cliente.getId());
        throw new ResourceNotFoundException("cliente", "id", Integer.toString(cliente.getId()));
    }
    
    public ResultSearchData<Cliente> search(PageablePrimitive pag, String parametro) {
        Pageable paging = PageRequest.of(pag.getPage(), pag.getSize(), pag.getSortOrder().equals("asc") ? Sort.by(pag.getSortBy()).ascending() : Sort.by(pag.getSortBy()).descending());
        Page<Cliente> pagedResult = repository.findAllBySharedKeyContains(paging, parametro);
        logger.trace("SEARCH - A TRACE Message");
        logger.debug("SEARCH - A TRACE Message");
        logger.info("SEARCH - A INFO Message");
        logger.warn("SEARCH - A WARN Message");
        logger.error("SEARCH - A ERR Message");
        return (ResultSearchData<Cliente>) this.getResultSearch(pagedResult);
    }
}
