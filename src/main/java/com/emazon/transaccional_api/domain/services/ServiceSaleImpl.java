package com.emazon.transaccional_api.domain.services;

import com.emazon.transaccional_api.domain.interfaces.ICartService;
import com.emazon.transaccional_api.domain.interfaces.IReportService;
import com.emazon.transaccional_api.domain.interfaces.ISaleService;
import com.emazon.transaccional_api.domain.interfaces.IStockService;
import com.emazon.transaccional_api.domain.model.ArticlesInCart;
import com.emazon.transaccional_api.domain.model.Items;
import com.emazon.transaccional_api.domain.util.ConstantsDomain;

import java.util.List;

public class ServiceSaleImpl  implements ISaleService{
    private final IStockService stockService;
    private final ICartService cartService;
    private final IReportService reportService;

    public ServiceSaleImpl(IStockService stockService, ICartService cartService,IReportService reportService){
        this.stockService=stockService;
        this.cartService=cartService;
        this.reportService=reportService;
    }
    @Override   
    public String saleArticlesInCart(){
        List<Items> items=cartService.getAllItemsToCart();
        List<ArticlesInCart> articles=stockService.reserveQuantityItems(items);
        articles.forEach(article -> {
            items.stream()
                    .filter(pc -> pc.getProductId().equals(article.getId()))
                    .findFirst()
                    .ifPresent(pc -> {
                        article.setQuantity(pc.getQuantity());
                    });
                });

        reportService.saveReport(articles);
        if(cartService.updateCart()){
            return ConstantsDomain.BUY_SUCESS ;
        }

         return ConstantsDomain.ERROR_NOT_HANDLER;
    }
}
