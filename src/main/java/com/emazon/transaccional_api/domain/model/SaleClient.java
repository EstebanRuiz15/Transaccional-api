package com.emazon.transaccional_api.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SaleClient {
    private String id;
    private String idClient;
    private String clientEmail;
    private LocalDateTime saleDateTime;
    private List<ArticlesInCart> items;
    private Double totalPaid;

    
}
