package com.emazon.transaccional_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@AllArgsConstructor 
@Getter
@Setter
public class Suply {

    private Integer id;
    private Integer articuloId;
    private Integer quantity;
    private Date date;

   
    

}