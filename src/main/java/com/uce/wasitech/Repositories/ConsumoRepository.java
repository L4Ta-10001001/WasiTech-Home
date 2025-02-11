package com.uce.wasitech.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uce.wasitech.Entities.Consumo;
import java.util.List;
import java.time.LocalDate;


public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
    List<Consumo> findByFechaRegistro(LocalDate fechaRegistro);
}
