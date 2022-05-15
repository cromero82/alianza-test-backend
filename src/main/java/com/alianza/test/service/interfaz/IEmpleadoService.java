package com.alianza.test.service.interfaz;

import com.alianza.test.model.entity.Empleado;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;

public interface IClienteService extends IGenericService<Empleado> {
    public ResultSearchData<Empleado> search(PageablePrimitive pag, String parametro);
}