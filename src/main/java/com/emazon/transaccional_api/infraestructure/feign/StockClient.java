package com.emazon.transaccional_api.infraestructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "stock-service", url = "http://localhost:8080")
public interface StockClient {
    @PostMapping("/article/increment")
    void incrementQuantity(@RequestParam Integer articleId, @RequestParam int cantidad);

    @PostMapping("/article/rollback")
    void rollback(@RequestParam Integer articleId, @RequestParam int cantidad);
}