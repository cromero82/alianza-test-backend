package com.alianza.test.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class EmpleadoDto {
    private int id;

    private String nombre;

    private String correo;
}
