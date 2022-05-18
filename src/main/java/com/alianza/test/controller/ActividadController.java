package com.alianza.test.controller;

import com.alianza.test.exception.InternalServerException;
import com.alianza.test.exception.ResourceNotFoundException;
import com.alianza.test.model.dto.ActividadDto;
import com.alianza.test.model.entity.Actividad;
import com.alianza.test.service.interfaz.IActividadService;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({ "v1/actividad-api" })
@CrossOrigin(origins= {"*"})
public class ActividadController {

    @Autowired
    public IActividadService service;

    @GetMapping("/healt")
    public ResponseEntity<String> health(){
        String entity = "ok running!";
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ActividadDto> create(@RequestBody ActividadDto actividad) throws ResourceNotFoundException, InternalServerException {
        return new ResponseEntity<>(service.create(actividad), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    public ResponseEntity<ActividadDto> findOne(@RequestParam int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ActividadDto> update(@RequestBody ActividadDto actividad) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.update(actividad), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> delete(@RequestParam int id) throws ResourceNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<ActividadDto>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResultSearchData<ActividadDto>> search(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size, @RequestParam(name = "sortBy") String sortBy,
                                                             @RequestParam(name = "sortOrder") String sortOrder, @RequestParam(name= "nombre") String nombre){
        PageablePrimitive pag = new PageablePrimitive(page, size,sortBy, sortOrder);
        ResultSearchData<ActividadDto> datos = service.search(pag, nombre);
        return new ResponseEntity<ResultSearchData<ActividadDto>>(datos, new HttpHeaders(), HttpStatus.OK);
    }
}
