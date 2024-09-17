package com.emazon.transaccional_api.domain.interfaces;

import com.emazon.transaccional_api.domain.model.Suply;

public interface IRepositotySupplyPort {
    String save(Suply suply);
    
}
