package com.emazon.transaccional_api.infraestructure.driving_http.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emazon.transaccional_api.infraestructure.driving_http.dtos.request.RequestSupplyAdd;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@Service
@RequestMapping("/suppliers")
public class ControllerAddSupply {

    private final TransaccionService transaccionService;

    @PostMapping("/")
    public ResponseEntity<String> realizarSuministro(@RequestBody RequestSupplyAdd request) {
        try {
            transaccionService.realizarSuministro(request);
            return ResponseEntity.ok("Suministro realizado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error en la transacción: " + e.getMessage());
        }
    }
}
