package com.emazon.transaccional_api.infraestructure.driven_repo.adapters;

import com.emazon.transaccional_api.domain.interfaces.IReportService;
import com.emazon.transaccional_api.domain.model.ArticlesInCart;
import com.emazon.transaccional_api.infraestructure.feign.ReportClient;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class ServiceFeignReportImpl implements IReportService {
    private final ReportClient reportClient;
    @Override
    public void saveReport(List<ArticlesInCart> articlesList){
        reportClient.saveReportSale(articlesList);
    }
}
