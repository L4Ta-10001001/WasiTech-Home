package com.uce.wasitech.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consumo_agua")
public class ConsumoAgua {

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
    @Column(name = "tipo_agua", nullable = false) // Potable, Reutilizada
    private String tipoAgua;

    @Getter
    @Setter
    @Column(name = "uso_agua", nullable = false) // Ducha, Riego, Lavado, etc.
    private String usoAgua;
}
