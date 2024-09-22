package com.emazon.transaccional_api.infraestructure.driven_repo.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emazon.transaccional_api.domain.interfaces.IStockService;
import com.emazon.transaccional_api.infraestructure.feign.StockClient;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ServiceFeignImple implements IStockService{
    
    private final StockClient stockClient;


    @Override
    public void incrementQuantity(Integer idArticle, Integer quantity) {
        stockClient.incrementQuantity(idArticle, quantity); 
    }
}
