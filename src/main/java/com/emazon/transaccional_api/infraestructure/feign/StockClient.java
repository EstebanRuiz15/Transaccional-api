package com.emazon.transaccional_api.infraestructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emazon.transaccional_api.domain.model.ArticlesInCart;
import com.emazon.transaccional_api.domain.model.Items;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;

@FeignClient(name = "apistock", url = "http://localhost:7070",configuration = FeignConfig.class)
public interface StockClient {
    @PatchMapping("article/increment")
    void incrementQuantity(@RequestParam("idArticle") Integer idArticle, @RequestParam("quantity") Integer quantity);
    @PostMapping(value = "article/reserve",consumes = "application/json")
    List<ArticlesInCart> reserveQuantityItems(@RequestBody List<Items> lisItems);
}