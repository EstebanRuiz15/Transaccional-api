package com.emazon.transaccional_api.infraestructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "apistock", url = "http://localhost:7070",configuration = FeignConfig.class)
public interface StockClient {
    @PatchMapping("article/increment")
    void incrementQuantity(@RequestParam("idArticle") Integer idArticle, @RequestParam("quantity") Integer quantity);

    @PatchMapping("article/rollback")
    void rollback(@RequestParam("idArticle") Integer idArticle, @RequestParam("quantity") Integer quantity);
}