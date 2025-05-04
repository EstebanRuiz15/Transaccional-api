package com.emazon.transaccional_api.infraestructure.driving_http.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emazon.transaccional_api.domain.interfaces.ISaleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/sales")
public class ControllerSale {
    private final ISaleService saleService;

    @Operation(summary = "Method for buy", description = " This method is to buy articles that the client have in your cart\n\n"
            + //
            "rules:\n\n" + //
            "       - If I buy and some items are out of stock, the entire transaction must be reversed.\n\n" + //
            "       - If I buy, the stock of the items must be discounted.\n\n"+
            "       -Once the purchase is made, the items must be removed from the cart as appropriate.\n\n"+
            "       -If an error occurs during the purchase process, you should revert all the changes made, so that there are no inconsistencies.\n\n"+
            "       -The purchase report (email of the person who bought, date of purchase, total cost, items purchased, and dates) must be recorded in the reporting microservice. All purchase information must be saved, including dates.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "buy sucessfull"),
            @ApiResponse(responseCode = "404", description = " Not found article or not stock for article"),
            @ApiResponse(responseCode = "401", description = " Unauthorized\n\n")

    })
    @PreAuthorize("hasRole('CLIENT')")
    @PostMapping("/")
    public ResponseEntity<String> saleArticlesInCart(){
        return ResponseEntity.ok(saleService.saleArticlesInCart());
    }
}
