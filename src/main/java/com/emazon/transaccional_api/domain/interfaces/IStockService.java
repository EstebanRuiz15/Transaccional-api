package com.emazon.transaccional_api.domain.interfaces;

import com.emazon.transaccional_api.domain.model.ArticlesInCart;
import com.emazon.transaccional_api.domain.model.Items;
import java.util.List;

public interface IStockService {
    void incrementQuantity(Integer idArticle, Integer quantity);
    List<ArticlesInCart> reserveQuantityItems(List<Items> lisItems);
}