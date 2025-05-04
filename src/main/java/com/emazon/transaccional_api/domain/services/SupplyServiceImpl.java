package com.emazon.transaccional_api.domain.services;

import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
import com.emazon.transaccional_api.domain.interfaces.IStockService;
import com.emazon.transaccional_api.domain.interfaces.ISupplyrService;
import com.emazon.transaccional_api.domain.model.Suply;

public class SupplyServiceImpl implements ISupplyrService {

    private final IStockService stockClient;
    private final IRepositotySupplyPort repository;

    public SupplyServiceImpl(IStockService stockClient, IRepositotySupplyPort repository) {
        this.stockClient = stockClient;
        this.repository = repository;
    }

    @Override
    public String addSupliers(Suply suply) {
        stockClient.incrementQuantity(suply.getArticuloId(), suply.getQuantity());
        return repository.save(suply);
    }
}