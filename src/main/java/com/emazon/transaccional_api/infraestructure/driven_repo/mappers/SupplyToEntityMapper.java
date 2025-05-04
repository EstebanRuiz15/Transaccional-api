package com.emazon.transaccional_api.infraestructure.driven_repo.mappers;

import org.mapstruct.Mapper;

import com.emazon.transaccional_api.domain.model.Suply;
import com.emazon.transaccional_api.infraestructure.driven_repo.entity.SupplyEntity;

@Mapper(componentModel = "spring")
public interface SupplyToEntityMapper {
    SupplyEntity toEntity(Suply suply);
}
