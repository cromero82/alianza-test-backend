package com.alianza.test.service.impl;

import com.alianza.test.exception.InternalServerException;
import com.alianza.test.exception.ResourceNotFoundException;
import com.alianza.test.model.dto.ActividadDto;
import com.alianza.test.model.entity.Actividad;
import com.alianza.test.model.repository.IActividadRepository;
import com.alianza.test.service.interfaz.IActividadService;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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
            actividad = definirDiasRetraso(actividad);
            actividad.setFechaCreacion(new Date());
            actividad = repository.save(actividad);

            return actividadDto;
        }catch (Exception ex){
            logger.error(loginTitle + " Error creando actividad > " + actividadDto.toString());
            throw new InternalServerException("Registrando actividad: " + actividadDto.getNombre() ,ex);
        }
    }

    private Actividad definirDiasRetraso(Actividad actividad) {
        if(actividad.getFechaFinalizacion() != null ) {
            actividad = obtenerDiferenciaDiasFechaPlaneada(actividad);
            actividad.setStatus(1);
        } else
        {
            actividad.setDiasRetraso( 0);
            actividad.setStatus(0);
        }
        return actividad;
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

            modelMapper = new ModelMapper();
            Actividad actividadEdicion = modelMapper.map(actividadOriginalOptional.get(), Actividad.class);
            actividadEdicion.setId( actividadOriginalOptional.get().getId() );
            actividadEdicion = definirDiasRetraso(actividadEdicion);
            repository.save(actividadEdicion);
            return actividadDto;
        }
        logger.error(loginTitle + "Error actualizando actividad (no encontrado) > id = " + actividadDto.getId());
        throw new ResourceNotFoundException("actividad", "id", Integer.toString(actividadDto.getId()));
    }
    
    public ResultSearchData<ActividadDto> search(PageablePrimitive pag, String parametro) {
        Pageable paging = PageRequest.of(pag.getPage(), pag.getSize(), pag.getSortOrder().equals("asc") ? Sort.by(pag.getSortBy()).ascending() : Sort.by(pag.getSortBy()).descending());
        Page<Actividad> pagedResult = repository.findAllByNombreContains(paging, parametro);

        // Calcula los dias de restraso para actividades que no estan finalizadas
        pagedResult.getContent().forEach( actividad -> {
            actividad = obtenerDiferenciaDiasFechaPlaneada(actividad);
        } );

        // Mapea al dto de salida
        ModelMapper modelMapper = new ModelMapper();
        List<ActividadDto> actividadesDto = new ArrayList<>();
        pagedResult.getContent().forEach( actividad -> actividadesDto.add( modelMapper.map(actividad, ActividadDto.class)) );
        Page<ActividadDto> listDto = pagedResult.map(actividad -> modelMapper.map(actividad, ActividadDto.class));

        return (ResultSearchData<ActividadDto>) this.getResultSearch(listDto);
    }

    /**
     * Obtiene la diferencia entre fechas con respecto a la fecha de finalizacion de tarea o la fecha de sistema (si no se ha finalizado)
     * @param actividad
     * @return
     */
    private Actividad obtenerDiferenciaDiasFechaPlaneada(Actividad actividad) {
        long fechaReferencia = actividad.getFechaFinalizacion() == null ? System.currentTimeMillis() :
                actividad.getFechaFinalizacion().getTime();

        long timeDiff = fechaReferencia - actividad.getFechaPlaneadaFinalizacion().getTime();
        long daysDiff = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
        if(daysDiff > 0){
            actividad.setDiasRetraso( (int) daysDiff);
        }else{
            actividad.setDiasRetraso(0);
        }
        return actividad;
    }
}
