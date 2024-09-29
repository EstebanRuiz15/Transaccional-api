package com.emazon.transaccional_api.domain.interfaces;
import java.util.List;

import com.emazon.transaccional_api.domain.model.Items;

public interface ICartService {
    List<Items> getAllItemsToCart();
    boolean updateCart();
}
