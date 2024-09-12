package com.emazon.transaccional_api.infraestructure.driving_http.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestSupplyAdd {
    
    private Integer idArticulo;
    private Integer quantity;


}
