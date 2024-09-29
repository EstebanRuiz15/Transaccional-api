package com.emazon.transaccional_api.infraestructure.driven_repo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "suministro")
public class SupplyEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "id_articulo")
    private Integer articuloId;
    @Column(name = "cantidad")
    private int quantity;
    @Column(name = "fecha")
    private Date date;

    public SupplyEntity() {
    }

    public SupplyEntity(int id, Integer articuloId, int quantity, Date date) {
        this.id = id;
        this.articuloId = articuloId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getArticuloId() {
        return articuloId;
    }

    public void setArticuloId(Integer articuloId) {
        this.articuloId = articuloId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    

}
