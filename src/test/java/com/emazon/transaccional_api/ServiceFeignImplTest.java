package com.emazon.transaccional_api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.emazon.transaccional_api.domain.exceptions.ErrorExceptionArticleNotFound;
import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;
import com.emazon.transaccional_api.domain.util.ConstantsDomain;
import com.emazon.transaccional_api.infraestructure.driven_repo.adapters.ServiceFeignStockImpl;
import com.emazon.transaccional_api.infraestructure.feign.StockClient;

import feign.FeignException;
import feign.RetryableException;

@SpringBootTest
public class ServiceFeignImplTest {
    
     @Mock
    private StockClient stockClient;

    @InjectMocks
    private ServiceFeignStockImpl serviceFeignImple;

    @BeforeEach
    void setUp() {
        stockClient=mock(StockClient.class);
        serviceFeignImple= new ServiceFeignStockImpl(stockClient);
    }

    @Test
    void testIncrementQuantitySuccess() {
       
        Integer idArticle = 1;
        Integer quantity = 5;

        assertDoesNotThrow(() -> serviceFeignImple.incrementQuantity(idArticle, quantity));

        verify(stockClient, times(1)).incrementQuantity(idArticle, quantity);
    }

    @Test
    void testIncrementQuantityRetryableException() {
       
        Integer idArticle = 1;
        Integer quantity = 5;
        doThrow(RetryableException.class).when(stockClient).incrementQuantity(idArticle, quantity);

        ErrorFeignException exception = assertThrows(ErrorFeignException.class, () ->
                serviceFeignImple.incrementQuantity(idArticle, quantity));

        assertEquals(ConstantsDomain.MICRO_NO_AVAILABLE, exception.getMessage());

        verify(stockClient, times(1)).incrementQuantity(idArticle, quantity);
    }

    @Test
    void testIncrementQuantityFeignExceptionNotFound() {
        
        Integer idArticle = 1;
        Integer quantity = 5;
        FeignException feignException = mock(FeignException.class);
        when(feignException.status()).thenReturn(404);
        doThrow(feignException).when(stockClient).incrementQuantity(idArticle, quantity);

        ErrorExceptionArticleNotFound exception = assertThrows(ErrorExceptionArticleNotFound.class, () ->
                serviceFeignImple.incrementQuantity(idArticle, quantity));

        assertEquals(ConstantsDomain.NOT_FOUND_ARTICLE + idArticle, exception.getMessage());

        verify(stockClient, times(1)).incrementQuantity(idArticle, quantity);
    }

    @Test
    void testIncrementQuantityOtherException() {
       
        Integer idArticle = 1;
    Integer quantity = 5;
    
    // Cambia Exception a RuntimeException o crea una excepción personalizada que extienda RuntimeException
    RuntimeException genericException = new RuntimeException("Unexpected Error");
    doThrow(genericException).when(stockClient).incrementQuantity(idArticle, quantity);

    // Asegúrate de que la llamada al método lance ErrorFeignException
    ErrorFeignException exception = assertThrows(ErrorFeignException.class, () ->
            serviceFeignImple.incrementQuantity(idArticle, quantity));

    // Verifica que el mensaje de la excepción contenga la cadena esperada
    assertTrue(exception.getMessage().contains("Unexpected Error"));

    // Verifica que el método incrementQuantity haya sido llamado una vez
    verify(stockClient, times(1)).incrementQuantity(idArticle, quantity);
    }
}
