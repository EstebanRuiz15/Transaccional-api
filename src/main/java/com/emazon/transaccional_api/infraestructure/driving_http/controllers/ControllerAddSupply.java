package com.emazon.transaccional_api.infraestructure.driving_http.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emazon.transaccional_api.domain.interfaces.ISupplyrService;
import com.emazon.transaccional_api.infraestructure.driving_http.dtos.request.RequestSupplyAdd;
import com.emazon.transaccional_api.infraestructure.driving_http.mappers.MapperSupply;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Service
@RequestMapping("/suppliers")
public class ControllerAddSupply {

    private final ISupplyrService supplyrService;
    private final MapperSupply mapper;

    @Operation(summary = "Method for add supply articles", description = " This method is to supply items, it can only access the auxiliary warehouse role, this method communicates with the stock microservice..\n\n"
            + //
            "rules:\n\n" + //
            "       - new supplies have to increase the amount of existing supplies\n\n" + //
            "       - If any error occurs, you must review the changes made, so that it does not affect the supply data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = " Not found article"),
            @ApiResponse(responseCode = "401", description = " Unauthorized\n\n")

    })
    @PreAuthorize("hasRole('AUX_BODEGA')")
    @PostMapping("/")
    public ResponseEntity<String> addSupply(@RequestBody RequestSupplyAdd request) {
        return ResponseEntity.ok(supplyrService.addSupliers(mapper.toSuply(request)));
    }

}
