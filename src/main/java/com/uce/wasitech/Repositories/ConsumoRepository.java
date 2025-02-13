package com.uce.wasitech.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uce.wasitech.Entities.Consumo;
import java.time.LocalDate;
import java.util.List;

public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
    List<Consumo> findByFechaRegistro(LocalDate fechaRegistro);
}
