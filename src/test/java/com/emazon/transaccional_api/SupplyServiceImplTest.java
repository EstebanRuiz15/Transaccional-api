package com.emazon.transaccional_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;
import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
import com.emazon.transaccional_api.domain.interfaces.IStockService;
import com.emazon.transaccional_api.domain.model.Suply;
import com.emazon.transaccional_api.domain.util.ConstantsDomain;
import com.emazon.transaccional_api.infraestructure.feign.StockClient;
import com.emazon.transaccional_api.domain.services.SupplyServiceImpl;

import feign.FeignException;
import feign.Request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import java.nio.charset.Charset;

import java.util.HashMap;

class SupplyServiceImplTest {

    @Mock
    private IStockService stockClient;

    @Mock
    private IRepositotySupplyPort repository;

    @InjectMocks
    private SupplyServiceImpl supplyService;

    private Suply suply;

    @BeforeEach
    void setUp() {
        stockClient=mock(IStockService.class);
        repository=mock(IRepositotySupplyPort.class);
        supplyService=new SupplyServiceImpl(stockClient, repository);

        suply = new Suply(1, 100, 10,null);
    }
    
    @Test
    void testAddSuppliers() {
        when(repository.save(suply)).thenReturn("Supply saved successfully");

        String result = supplyService.addSupliers(suply);

        verify(stockClient, times(1)).incrementQuantity(suply.getArticuloId(), suply.getQuantity());

        verify(repository, times(1)).save(suply);

        assertEquals("Supply saved successfully", result);
    }
   
}