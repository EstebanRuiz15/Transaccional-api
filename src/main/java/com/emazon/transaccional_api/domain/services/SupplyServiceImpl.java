package com.emazon.transaccional_api.domain.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emazon.transaccional_api.domain.exceptions.ErrorExceptionParam;
import com.emazon.transaccional_api.domain.exceptions.ErrorFeignException;
import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
import com.emazon.transaccional_api.domain.interfaces.ISupplyrService;
import com.emazon.transaccional_api.domain.model.Suply;
import com.emazon.transaccional_api.domain.util.ConstantsDomain;
import com.emazon.transaccional_api.infraestructure.feign.StockClient;

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
    public String addSupliers(Suply suply) {
        try {
            stockClient.incrementQuantity(suply.getArticuloId(), suply.getQuantity());

            return repository.save(suply);
        } catch (FeignException e) {
            if (e.status() == HttpStatus.NOT_FOUND.value()) {
                throw new ErrorFeignException(ConstantsDomain.No_ARTICLE_FOUND_EXCEPTION);
            } else if (e.status() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                throw new ErrorFeignException(ConstantsDomain.ERROR_WITH_THE_OTHER_MICRO);
            } else {
                throw new ErrorFeignException(
                        ConstantsDomain.ERROR_SERVICE_OFF + e.getMessage());
            }
        }

    }
}
