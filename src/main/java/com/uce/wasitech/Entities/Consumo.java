package com.uce.wasitech.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
public class Consumo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)

  @Getter
  @Setter
  @Column(name = "id")
  private Long id;

  @Getter
  @Setter
  @Column(name = "tipoRecurso") // Energía, Agua, Gas, etc.
  private String tipoRecurso;

  @Getter
  @Setter
  @Column(name = "cantidad") // kWh, Litros, m³, kg
  private String cantidad;

  @Getter
  @Setter
  @Column(name = "fechaRegistro")
  private LocalDate fechaRegistro;

}
