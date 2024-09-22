package com.emazon.transaccional_api.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;
import com.emazon.transaccional_api.domain.interfaces.ICircuitBreakerService;
import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
import com.emazon.transaccional_api.domain.interfaces.IStockService;
import com.emazon.transaccional_api.domain.interfaces.ISupplyrService;
import com.emazon.transaccional_api.domain.model.Suply;
import com.emazon.transaccional_api.domain.util.ConstantsDomain;
import feign.FeignException;
import feign.RetryableException;

@Service

public class SupplyServiceImpl implements ISupplyrService {

    private final IStockService stockClient;
    private final IRepositotySupplyPort repository;
    private final ICircuitBreakerService breakService;

    public SupplyServiceImpl(IStockService stockClient, IRepositotySupplyPort repository,ICircuitBreakerService breakService) {
        this.stockClient = stockClient;
        this.repository = repository;
        this.breakService=breakService;
    }

    @Override
    public String addSupliers(Suply suply) {
        return breakService.executeWithCircuitBreaker(
           ConstantsDomain.STOCK_CLIENT , 
            () -> {
                stockClient.incrementQuantity(suply.getArticuloId(), suply.getQuantity());
                return repository.save(suply);
            }, 
            throwable -> fallbackAddSuppliers(suply, throwable)
        );
    }

    public String fallbackAddSuppliers(Suply suply, Throwable throwable) {
        if (throwable instanceof RetryableException) {
            throw new ErrorFeignException(ConstantsDomain.MICRO_NO_AVAILABLE);
        }
        if (throwable instanceof FeignException) {
            FeignException feignException = (FeignException) throwable;
            int status = feignException.status();

            if (status == HttpStatus.NOT_FOUND.value()) {
                throw new ErrorFeignException( ConstantsDomain.NOT_FOUND_ARTICLE+ suply.getArticuloId());
            } else if (status == HttpStatus.SERVICE_UNAVAILABLE.value()) {
                throw new ErrorFeignException(ConstantsDomain.MICRO_NO_AVAILABLE);
            } else if (status == HttpStatus.UNAUTHORIZED.value()) {
                throw new ErrorFeignException(ConstantsDomain.NOT_AUTHORIZED_AT_THIS_SERVICE);
            } else {
                throw new ErrorFeignException( ConstantsDomain.ERROR_NOT_HANDLER+ feignException.getMessage());
            }
        }
        return ConstantsDomain.ERROR_NOT_HANDLER;
    }
}