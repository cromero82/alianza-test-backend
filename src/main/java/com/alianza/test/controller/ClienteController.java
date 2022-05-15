package com.alianza.test.controller;

import com.alianza.test.exception.InternalServerException;
import com.alianza.test.exception.ResourceNotFoundException;
import com.alianza.test.model.entity.Empleado;
import com.alianza.test.service.interfaz.IClienteService;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping({ "v1/empleado-api" })
@CrossOrigin(origins= {"*"})
public class ClienteController {

    @Autowired
    public IClienteService service;

    @GetMapping("/healt")
    public ResponseEntity<String> health(){
        String entity = "ok running!";
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Empleado> create(@RequestBody Empleado Cliente) throws ResourceNotFoundException, InternalServerException {
        return new ResponseEntity<>(service.create(Cliente), HttpStatus.CREATED);
    }

    @GetMapping("/findById")
    public ResponseEntity<Empleado> findOne(@RequestParam int id) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Empleado> update(@RequestBody Empleado Cliente) throws ResourceNotFoundException {
        return new ResponseEntity<>(service.update(Cliente), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Object> delete(@RequestParam int id) throws ResourceNotFoundException {
        service.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<Iterable<Empleado>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<ResultSearchData<Empleado>> search(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "10") int size, @RequestParam(name = "sortBy") String sortBy,
                                                             @RequestParam(name = "sortOrder") String sortOrder, @RequestParam(name= "nombre") String nombre){
        PageablePrimitive pag = new PageablePrimitive(page, size,sortBy, sortOrder);
        ResultSearchData<Empleado> datos = service.search(pag, nombre);
        return new ResponseEntity<ResultSearchData<Empleado>>(datos, new HttpHeaders(), HttpStatus.OK);
    }
}
