package com.emazon.transaccional_api.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;
import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
import com.emazon.transaccional_api.domain.interfaces.ISupplyrService;
import com.emazon.transaccional_api.domain.model.Suply;
import com.emazon.transaccional_api.domain.util.ConstantsDomain;
import com.emazon.transaccional_api.infraestructure.feign.StockClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import feign.FeignException;

@Service
public class SupplyServiceImpl implements ISupplyrService {

    private final StockClient stockClient;
    private final IRepositotySupplyPort repository;

    public SupplyServiceImpl(StockClient stockClient, IRepositotySupplyPort repository) {
        this.stockClient = stockClient;
        this.repository = repository;
    }

    @Override
    @CircuitBreaker(name = "stockClient", fallbackMethod = "fallbackAddSuppliers")
    public String addSupliers(Suply suply) {
        stockClient.incrementQuantity(suply.getArticuloId(), suply.getQuantity());
        return repository.save(suply);
    }

    public String fallbackAddSuppliers(Suply suply, Throwable throwable) {

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
