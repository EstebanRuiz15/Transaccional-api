package com.emazon.transaccional_api.infraestructure.driven_repo.adapters;

import org.springframework.stereotype.Service;

import com.emazon.transaccional_api.domain.interfaces.IRepositotySupplyPort;
import com.emazon.transaccional_api.domain.model.Suply;
import com.emazon.transaccional_api.infraestructure.driven_repo.mappers.SupplyToEntityMapper;
import com.emazon.transaccional_api.infraestructure.driven_repo.persistence.IRepositorySupplyJpa;
import com.emazon.transaccional_api.infraestructure.driving_http.util.ConstantsInfra;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RepositorySuministerImpl implements IRepositotySupplyPort {
    private final SupplyToEntityMapper mapper;
    private final IRepositorySupplyJpa repositoryJpa;
    @Override
    public String save(Suply suply) {
        suply.setDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
        repositoryJpa.save(mapper.toEntity(suply));
        return ConstantsInfra.SUPPLY_SAVE_EXIT;
    }
    

    
}
