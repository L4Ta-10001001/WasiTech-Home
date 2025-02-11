package com.uce.wasitech.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uce.wasitech.Repositories.ConsumoRepository;
import com.uce.wasitech.Entities.Consumo;

@RestController
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoRepository ConsumoRepository;

    @GetMapping
    public List<Consumo> obtenerConsumos() {
        return ConsumoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Consumo obtenerConsumos(@PathVariable Long id) {
        return ConsumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el consumo con el ID: " + id));
    }

    @PostMapping
    public Consumo crearConsumo(@RequestBody Consumo consumo) {
        return ConsumoRepository.save(consumo);
    }

    @PutMapping("/{id}")
    public Consumo actualizarConsumo(@PathVariable Long id, @RequestBody Consumo detalleConsumo) {
        Consumo consumo = ConsumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el consumo con el ID: " + id));

        consumo.setTipoRecurso(detalleConsumo.getTipoRecurso());
        consumo.setCantidad(detalleConsumo.getCantidad());
        consumo.setFechaRegistro(detalleConsumo.getFechaRegistro());

        return ConsumoRepository.save(consumo);
    }

    @DeleteMapping("/{id}")
    public String borrarConsumo(@PathVariable Long id) {
        Consumo consumo = ConsumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el consumo con el ID: " + id));

        ConsumoRepository.delete(consumo);
        return "El consumo con el ID: " + id + " fue eliminado correctamente";
    }

}
