package com.alianza.test.curso;

import com.alianza.test.shared.Suma;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SumaTest {
    Suma suma = new Suma();

    @Test
    public void sumarTest(){
        int resultado = suma.sumar(2,3);
        int resultadoEsperado = 5;
        assertEquals(resultadoEsperado, resultado);
    }
}
