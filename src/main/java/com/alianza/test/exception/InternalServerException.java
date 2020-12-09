package com.alianza.test.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = -7509737619336894337L;

    public InternalServerException( String mensaje, Exception ex) {
        super("excepcion-control: Error "+ mensaje + "\n Mensaje: " + ex.getMessage() + ", \n causado por: " + ex.getCause() + ", \n");
    }
}