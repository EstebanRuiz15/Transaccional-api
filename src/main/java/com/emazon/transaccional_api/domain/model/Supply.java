package com.emazon.transaccional_api.domain.model;

public class Supply {

    private Integer id;
    private Integer idArticulo;
    private Integer quantity;

    public Supply() {
    }

    public Supply(Integer id, Integer idArticulo, Integer quantity) {
        this.id = id;
        this.idArticulo = idArticulo;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

   

}
