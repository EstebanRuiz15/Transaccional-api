package com.emazon.transaccional_api.domain.services;

import com.emazon.transaccional_api.domain.interfaces.ISupplyrService;
import com.emazon.transaccional_api.domain.model.Supply;
import com.emazon.transaccional_api.infraestructure.feign.StockClient;

public class SupplyService implements ISupplyrService {

    private final StockClient stockClient;

    public SupplyService(StockClient stockClient) {
        this.stockClient = stockClient;
    }

    @Override
    public String addSupliers(Supply suply) {
        try {
            // Llamada al microservicio de Stock para incrementar el suministro
            stockClient.incrementQuantity(suply.getIdArticulo(),suply.getQuantity());
        } catch (Exception e) {
            // Manejar el error (puedes lanzar una excepción personalizada)
            // Aquí podrías iniciar una operación de compensación
            stockClient.rollback(suply.getIdArticulo(),suply.getQuantity());
            throw new ErrorException("Error al procesar el suministro, cambios revertidos");
        }
    }

}
