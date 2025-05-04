package com.emazon.transaccional_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
import com.emazon.transaccional_api.domain.interfaces.IStockService;
import com.emazon.transaccional_api.domain.model.Suply;
import com.emazon.transaccional_api.domain.services.SupplyServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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