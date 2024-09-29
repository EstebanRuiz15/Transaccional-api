package com.emazon.transaccional_api.infraestructure.driving_http.dtos.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestSupplyAdd {

    @NotBlank
    private Integer articuloId;
    @NotBlank
    private Integer quantity;

}
