package com.emazon.transaccional_api.aplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.emazon.transaccional_api.domain.interfaces.ICartService;
import com.emazon.transaccional_api.domain.interfaces.IReportService;
import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
import com.emazon.transaccional_api.domain.interfaces.IStockService;
import com.emazon.transaccional_api.domain.services.ServiceSaleImpl;
import com.emazon.transaccional_api.domain.services.SupplyServiceImpl;

@Configuration
public class BeanConfig {

    @Bean
    public SupplyServiceImpl getSupplyServiceImpl(IStockService stockClient, IRepositotySupplyPort repository) {
        return new SupplyServiceImpl(stockClient, repository);
    }

    @Bean
    public ServiceSaleImpl getServiceSaleImpl(IStockService stockService, ICartService cartService, IReportService reportService){
        return new ServiceSaleImpl(stockService, cartService,reportService);
    }

}
