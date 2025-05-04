package com.emazon.transaccional_api.infraestructure.driven_repo.adapters;
import org.springframework.stereotype.Component;

import java.util.List;
import com.emazon.transaccional_api.domain.interfaces.ICartService;
import com.emazon.transaccional_api.domain.model.Items;
import com.emazon.transaccional_api.infraestructure.feign.CartClient;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ServiceFeignCart implements ICartService {
    private final CartClient cartClient;
    @Override
    public List<Items> getAllItemsToCart(){
        return cartClient.getItems();
    }

    @Override
    public boolean updateCart(){
       return cartClient.deleteItemsToCart();
    }

}
