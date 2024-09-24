package com.emazon.transaccional_api.infraestructure.driven_repo.adapters;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.emazon.transaccional_api.domain.exceptions.ErrorExceptionArticleNotFound;
import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;
import com.emazon.transaccional_api.domain.interfaces.IStockService;
import com.emazon.transaccional_api.domain.util.ConstantsDomain;
import com.emazon.transaccional_api.infraestructure.feign.StockClient;

import feign.FeignException;
import feign.RetryableException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ServiceFeignImple implements IStockService {

    private final StockClient stockClient;

    @Override
    public void incrementQuantity(Integer idArticle, Integer quantity) {
        try {
            
            stockClient.incrementQuantity(idArticle, quantity);
        } catch (RetryableException retryableException) {
           
            throw new ErrorFeignException(ConstantsDomain.MICRO_NO_AVAILABLE);
        } catch (FeignException feignException) {
            
            int status = feignException.status();

            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new ErrorExceptionArticleNotFound(ConstantsDomain.NOT_FOUND_ARTICLE + idArticle);
            }
        } catch (Exception ex) {
            throw new ErrorFeignException(ConstantsDomain.ERROR_NOT_HANDLER + ex.getMessage());
        }
    }
}