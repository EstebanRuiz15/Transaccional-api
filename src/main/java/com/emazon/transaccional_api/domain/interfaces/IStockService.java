package com.emazon.transaccional_api.domain.interfaces;

public interface IStockService {
    void incrementQuantity(Integer idArticle, Integer quantity);
}