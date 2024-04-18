package com.queuepass.apirest.model;

import jakarta.persistence.*;

@Entity
@Table
public class Plate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Column(unique = true)
    private String name;
    private Double price;
    private String description;

    public Plate(Long id, String type, String name, Double price, String description) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Plate() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
