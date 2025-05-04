package com.emazon.transaccional_api.domain.interfaces;

import com.emazon.transaccional_api.domain.model.ArticlesInCart;
import java.util.List; 

public interface IReportService {
    void saveReport(List<ArticlesInCart> articlesList);
}
