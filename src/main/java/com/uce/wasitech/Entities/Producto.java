package com.uce.wasitech.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Getter
    @Setter
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "nombre")
    private String nombre;

    @Getter
    @Setter
    @Column(name = "precio")
    private String precio;

}
