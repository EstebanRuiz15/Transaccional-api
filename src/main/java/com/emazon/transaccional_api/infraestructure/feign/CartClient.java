package com.emazon.transaccional_api.infraestructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.emazon.transaccional_api.domain.model.Items;

import java.util.List;
@FeignClient(name = "Micro-Cart", url = "http://localhost:6060",configuration = FeignConfig.class)
public interface CartClient {

    @GetMapping("cart/itemsId")
    List<Items> getItems();
    @PostMapping("cart/update")
    boolean deleteItemsToCart();

}
