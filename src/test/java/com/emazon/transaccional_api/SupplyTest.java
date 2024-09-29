package com.emazon.transaccional_api;

import org.springframework.boot.test.context.SpringBootTest;

import com.emazon.transaccional_api.domain.model.Suply;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

@SpringBootTest
public class SupplyTest {

     @Test
    void testConstructorAndGetters() {
        
        Integer id = 1;
        Integer articuloId = 100;
        Integer quantity = 50;
        Date date = new Date();

        Suply suply = new Suply(id, articuloId, quantity, date);

        assertEquals(id, suply.getId());
        assertEquals(articuloId, suply.getArticuloId());
        assertEquals(quantity, suply.getQuantity());
        assertEquals(date, suply.getDate());
    }

    @Test
    void testSetters() {
        Suply suply = new Suply();

        Integer id = 1;
        Integer articuloId = 100;
        Integer quantity = 50;
        Date date = new Date();

        suply.setId(id);
        suply.setArticuloId(articuloId);
        suply.setQuantity(quantity);
        suply.setDate(date);

        assertEquals(id, suply.getId());
        assertEquals(articuloId, suply.getArticuloId());
        assertEquals(quantity, suply.getQuantity());
        assertEquals(date, suply.getDate());
    }
}
