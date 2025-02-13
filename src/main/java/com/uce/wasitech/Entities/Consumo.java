package com.uce.wasitech.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "consumo")
public class Consumo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  @Column(name = "id")
  private Long id;

  @ManyToOne

  @JoinColumn(name = "id_usuario", nullable = false)
  @Getter
  @Setter
  private Usuario usuario; // Relación con el usuario
  
  @Getter
  @Setter
  @Column(name = "tipo_recurso", nullable = false) // Energía, Agua, Gas, Desperdicios
  private String tipoRecurso;

  @Getter
  @Setter
  @Column(name = "cantidad", nullable = false) // kWh, Litros, m³, kg
  private Double cantidad;

  @Getter
  @Setter
  @Column(name = "unidad_medida", nullable = false) // kWh, Litros, m³, kg
  private String unidadMedida;

  @Getter
  @Setter
  @Column(name = "costo_estimado")
  private Double costoEstimado;

  @Getter
  @Setter
  @Column(name = "fuente") // Red Eléctrica, Solar, Agua Potable, GLP, etc.
  private String fuente;

  @Getter
  @Setter
  @Column(name = "fecha_registro", nullable = false)
  private LocalDate fechaRegistro;

}
