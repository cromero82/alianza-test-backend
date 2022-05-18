package com.alianza.test.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ActividadDto {

    private int id;

    private String nombre;

    private int status;

    private EmpleadoDto empleadoAsignado;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "es_CO", timezone = "GMT-5")
    private Date fechaCreacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "es_CO", timezone = "GMT-5")
    private Date fechaPlaneadaFinalizacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "es_CO", timezone = "GMT-5")
    private Date fechaFinalizacion;

    @JsonIgnore
    private Date eliminado = new Date("1999/09/09");

    private int diasRetraso;
}
