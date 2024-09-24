package com.emazon.transaccional_api.domain.model;

import java.util.Date;


public class Suply {

    private Integer id;
    private Integer articuloId;
    private Integer quantity;
    private Date date;

    public Suply() {
    }

    public Suply(Integer id, Integer articuloId, Integer quantity, Date date) {
        this.id = id;
        this.articuloId = articuloId;
        this.quantity = quantity;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Integer articuloId) {
        this.articuloId = articuloId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
   
    

}