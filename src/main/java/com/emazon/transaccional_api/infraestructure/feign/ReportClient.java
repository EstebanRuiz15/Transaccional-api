package com.emazon.transaccional_api.infraestructure.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import com.emazon.transaccional_api.domain.model.ArticlesInCart;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@FeignClient(name = "Api-Reportes", url = "http://localhost:9090",configuration = FeignConfig.class)
public interface ReportClient {
@PostMapping(value = "report/", consumes = "application/json")
    void saveReportSale(@RequestBody List<ArticlesInCart> sale);
}
