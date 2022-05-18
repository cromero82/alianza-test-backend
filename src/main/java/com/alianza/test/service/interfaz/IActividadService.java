package com.alianza.test.service.interfaz;

import com.alianza.test.model.dto.ActividadDto;
import com.alianza.test.model.entity.Actividad;
import com.alianza.test.model.entity.Empleado;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;

public interface IActividadService extends IGenericService<ActividadDto> {
    public ResultSearchData<ActividadDto> search(PageablePrimitive pag, String parametro);
}