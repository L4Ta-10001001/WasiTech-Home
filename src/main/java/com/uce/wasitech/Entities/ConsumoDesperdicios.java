package com.uce.wasitech.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consumo_desperdicios")
public class ConsumoDesperdicios {

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
    @Column(name = "tipo_residuo", nullable = false) // Orgánico, Reciclable, No Reciclable
    private String tipoResiduo;

    @Getter
    @Setter
    @Column(name = "metodo_eliminacion", nullable = false) // Reciclaje, Compostaje, Basura Común
    private String metodoEliminacion;
}
