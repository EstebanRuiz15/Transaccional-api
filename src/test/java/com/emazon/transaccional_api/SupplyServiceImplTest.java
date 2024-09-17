package com.emazon.transaccional_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;
import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
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
    private StockClient stockClient;

    @Mock
    private IRepositotySupplyPort repository;

    @InjectMocks
    private SupplyServiceImpl supplyService;

    private Suply suply;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear objeto Supply de prueba
        suply = new Suply(1, 100, 10,null);
    }

    @Test
    void testAddSuppliersSuccess() {
        // Configurar el comportamiento esperado del repository para un caso exitoso
        when(repository.save(suply)).thenReturn("Supply saved successfully");

        // Ejecutar el método
        String result = supplyService.addSupliers(suply);

        // Verificar que el cliente Feign fue invocado correctamente
        verify(stockClient).incrementQuantity(suply.getArticuloId(), suply.getQuantity());

        // Verificar que el resultado sea correcto
        assertEquals("Supply saved successfully", result);
    }

    @Test
    void testAddSuppliersArticleNotFound() {
        // Simular una excepción 404 cuando se llama al cliente Feign
        FeignException notFoundException = FeignException.errorStatus("incrementQuantity",
                feign.Response.builder()
                        .status(HttpStatus.NOT_FOUND.value())
                        .headers(new HashMap<>())
                        .request(Request.create(Request.HttpMethod.GET, "/path", new HashMap<>(), new byte[0], Charset.defaultCharset()))
                        .build());

        doThrow(notFoundException).when(stockClient).incrementQuantity(anyInt(), anyInt());

        // Ejecutar y verificar que se lance la excepción esperada
        ErrorFeignException exception = assertThrows(ErrorFeignException.class, () -> supplyService.addSupliers(suply));

        // Verificar que el mensaje de error sea el correcto
        assertEquals(ConstantsDomain.No_ARTICLE_FOUND_EXCEPTION, exception.getMessage());
    }

    @Test
    void testAddSuppliersInternalServerError() {
        // Simular una excepción 500 cuando se llama al cliente Feign
        FeignException internalServerErrorException = FeignException.errorStatus("incrementQuantity",
                feign.Response.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .headers(new HashMap<>())
                        .request(Request.create(Request.HttpMethod.GET, "/path", new HashMap<>(), new byte[0], Charset.defaultCharset()))
                        .build());

        doThrow(internalServerErrorException).when(stockClient).incrementQuantity(anyInt(), anyInt());

        // Ejecutar y verificar que se lance la excepción esperada
        ErrorFeignException exception = assertThrows(ErrorFeignException.class, () -> supplyService.addSupliers(suply));

        // Verificar que el mensaje de error sea el correcto
        assertEquals(ConstantsDomain.ERROR_WITH_THE_OTHER_MICRO, exception.getMessage());
    }

    @Test
    void testAddSuppliersServiceOff() {
        // Simular una excepción con otro estado de error para Feign
        FeignException unknownException = FeignException.errorStatus("incrementQuantity",
                feign.Response.builder()
                        .status(HttpStatus.SERVICE_UNAVAILABLE.value())
                        .headers(new HashMap<>())
                        .request(Request.create(Request.HttpMethod.GET, "/path", new HashMap<>(), new byte[0], Charset.defaultCharset()))
                        .build());

        doThrow(unknownException).when(stockClient).incrementQuantity(anyInt(), anyInt());

        // Ejecutar y verificar que se lance la excepción esperada
        ErrorFeignException exception = assertThrows(ErrorFeignException.class, () -> supplyService.addSupliers(suply));

        // Verificar que el mensaje de error sea el correcto
        assertEquals(ConstantsDomain.ERROR_SERVICE_OFF + unknownException.getMessage(), exception.getMessage());
    }
}