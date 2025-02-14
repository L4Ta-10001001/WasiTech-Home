package com.uce.wasitech.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consumo_gas")
public class ConsumoGas {

    @Id
    @Getter
    @Setter
    private Long id; // Se usará el mismo ID que en "Consumo"

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    @Getter
    @Setter
    private Consumo consumo; // Relación con la tabla principal Consumo

    @Getter
    @Setter
    @Column(name = "tipo_gas", nullable = false) // GLP, Natural
    private String tipoGas;

    @Getter
    @Setter
    @Column(name = "uso_gas", nullable = false) // Cocina, Calefacción, Agua Caliente
    private String usoGas;
}
