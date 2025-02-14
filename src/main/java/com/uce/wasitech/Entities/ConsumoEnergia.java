package com.uce.wasitech.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "consumo_energia")
public class ConsumoEnergia {

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
  @Column(name = "fuente_energia", nullable = false) // Red Eléctrica, Solar, Mixta
  private String fuenteEnergia;

  @Getter
  @Setter
  @Column(name = "hora_pico", nullable = false) // Si el consumo ocurrió en horas pico (true/false)
  private Boolean horaPico;

  @Getter
  @Setter
  @Column(name = "uso_dispositivos") // Electrodomésticos, Iluminación, etc.
  private String usoDispositivos;
}