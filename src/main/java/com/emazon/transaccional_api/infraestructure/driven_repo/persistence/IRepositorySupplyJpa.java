package com.emazon.transaccional_api.infraestructure.driven_repo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emazon.transaccional_api.infraestructure.driven_repo.entity.SupplyEntity;

public interface IRepositorySupplyJpa extends JpaRepository<SupplyEntity, Integer>{
    
}
