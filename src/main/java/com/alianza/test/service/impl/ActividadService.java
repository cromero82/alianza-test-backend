package com.alianza.test.service.impl;

import com.alianza.test.exception.InternalServerException;
import com.alianza.test.exception.ResourceNotFoundException;
import com.alianza.test.model.dto.ActividadDto;
import com.alianza.test.model.entity.Actividad;
import com.alianza.test.model.repository.IActividadRepository;
import com.alianza.test.service.interfaz.IActividadService;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActividadService extends BaseService<ActividadDto> implements IActividadService {
    @Autowired
    private IActividadRepository repository;

    ModelMapper modelMapper = new ModelMapper();

    private static final Logger logger = LoggerFactory.getLogger(ActividadService.class);

    @Value( "${logging.custom.title}" )
    private String loginTitle;

    @Override
    public ActividadDto create(ActividadDto actividadDto) throws InternalServerException {
        try {
            modelMapper = new ModelMapper();
            Actividad actividad = modelMapper.map(actividadDto, Actividad.class);
            actividad = repository.save(actividad);
//            actividadDto.setId( actividad.getId());

            return actividadDto;
        }catch (Exception ex){
            logger.error(loginTitle + " Error creando actividad > " + actividadDto.toString());
            throw new InternalServerException("Registrando actividad: " + actividadDto.getNombre() ,ex);
        }
    }

    @Override
    public void deleteById(int id) throws ResourceNotFoundException {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            logger.error(loginTitle + " Error eliminando actividad > id: " + id);
            throw new ResourceNotFoundException("actividad > id: " + id);
        }
    }

    @Override
    public List<ActividadDto> findAll() {
        Iterable<Actividad> actividades = repository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<ActividadDto> actividadesDto = new ArrayList<>();
        actividades.forEach( actividad -> actividadesDto.add( modelMapper.map(actividad, ActividadDto.class)));
        return actividadesDto;
    }

    @Override
    public ActividadDto findById(int id) throws ResourceNotFoundException {
        Optional<Actividad> actividad = repository.findById(id);
        if (actividad.isPresent()) {
            modelMapper = new ModelMapper();
            ActividadDto actividadDto = modelMapper.map(actividad.get(), ActividadDto.class);
            return actividadDto;
        } else {
            logger.warn(loginTitle + "Advertencia consultando > id: " + id);
            throw new ResourceNotFoundException("actividad", "id", Integer.toString(id));
        }
    }

    @Override
    public ActividadDto update(ActividadDto actividadDto) throws ResourceNotFoundException {
        Optional<Actividad> actividadOriginalOptional = repository.findById(actividadDto.getId());
        if (actividadOriginalOptional.isPresent()) {
//            Actividad actividadEdicion = actividadOriginalOptional.get();
//            actividadEdicion.setNombre();
            modelMapper = new ModelMapper();
            Actividad actividadEdicion = modelMapper.map(actividadOriginalOptional.get(), Actividad.class);
            actividadEdicion.setId( actividadOriginalOptional.get().getId() );
            actividadEdicion = repository.save(actividadEdicion);
            return actividadDto;
        }
        logger.error(loginTitle + "Error actualizando actividad (no encontrado) > id = " + actividadDto.getId());
        throw new ResourceNotFoundException("actividad", "id", Integer.toString(actividadDto.getId()));
    }
    
    public ResultSearchData<ActividadDto> search(PageablePrimitive pag, String parametro) {
        Pageable paging = PageRequest.of(pag.getPage(), pag.getSize(), pag.getSortOrder().equals("asc") ? Sort.by(pag.getSortBy()).ascending() : Sort.by(pag.getSortBy()).descending());
        Page<Actividad> pagedResult = repository.findAllByNombreContains(paging, parametro);
        ModelMapper modelMapper = new ModelMapper();
        List<ActividadDto> actividadesDto = new ArrayList<>();
        pagedResult.getContent().forEach( actividad -> actividadesDto.add( modelMapper.map(actividad, ActividadDto.class)) );
        Page<ActividadDto> listDto = pagedResult.map(actividad -> modelMapper.map(actividad, ActividadDto.class));
        return (ResultSearchData<ActividadDto>) this.getResultSearch(listDto);
    }
}
