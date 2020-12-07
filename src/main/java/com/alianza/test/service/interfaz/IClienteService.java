package com.alianza.test.service.interfaz;

import com.alianza.test.model.entity.Cliente;
import com.alianza.test.shared.PageablePrimitive;
import com.alianza.test.shared.ResultSearchData;

public interface IClienteService extends IGenericService<Cliente> {
    public ResultSearchData<Cliente> search(PageablePrimitive pag, String parametro);
}