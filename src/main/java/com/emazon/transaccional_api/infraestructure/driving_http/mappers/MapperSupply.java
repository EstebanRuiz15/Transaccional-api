package com.emazon.transaccional_api.infraestructure.driving_http.mappers;

import org.mapstruct.Mapper;

import com.emazon.transaccional_api.domain.model.Suply;
import com.emazon.transaccional_api.infraestructure.driving_http.dtos.request.RequestSupplyAdd;

@Mapper(componentModel = "spring")
public interface MapperSupply {
    
    Suply toSuply(RequestSupplyAdd request);
}
