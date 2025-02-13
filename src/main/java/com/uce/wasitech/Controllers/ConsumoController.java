package com.uce.wasitech.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.uce.wasitech.Repositories.*;
import com.uce.wasitech.Entities.*;

@RestController
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private ConsumoEnergiaRepository consumoEnergiaRepository;

    @Autowired
    private ConsumoAguaRepository consumoAguaRepository;

    @Autowired
    private ConsumoGasRepository consumoGasRepository;

    @Autowired
    private ConsumoDesperdiciosRepository consumoDesperdiciosRepository;

    @GetMapping
    public List<Consumo> obtenerConsumos() {
        return consumoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Consumo obtenerConsumo(@PathVariable Long id) {
        return consumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el consumo con el ID: " + id));
    }

    @PostMapping
    public Consumo crearConsumo(@RequestBody Consumo consumo) {
        // Guardamos el consumo en la tabla principal
        consumo = consumoRepository.save(consumo);

        // Insertamos en la tabla correspondiente según el tipo de recurso
        switch (consumo.getTipoRecurso().toLowerCase()) {
            case "energía":
                ConsumoEnergia energia = new ConsumoEnergia();
                energia.setConsumo(consumo);
                energia.setFuenteEnergia(consumo.getFuente());
                energia.setHoraPico(false); // Valor por defecto, se debe recibir en la petición
                energia.setUsoDispositivos("Desconocido"); // Valor por defecto, debe recibirse en la petición
                consumoEnergiaRepository.save(energia);
                break;

            case "agua":
                ConsumoAgua agua = new ConsumoAgua();
                agua.setConsumo(consumo);
                agua.setTipoAgua("Potable"); // Valor por defecto, debe recibirse en la petición
                agua.setUsoAgua("General"); // Valor por defecto, debe recibirse en la petición
                consumoAguaRepository.save(agua);
                break;

            case "gas":
                ConsumoGas gas = new ConsumoGas();
                gas.setConsumo(consumo);
                gas.setTipoGas("GLP"); // Valor por defecto, debe recibirse en la petición
                gas.setUsoGas("Cocina"); // Valor por defecto, debe recibirse en la petición
                consumoGasRepository.save(gas);
                break;

            case "desperdicios":
                ConsumoDesperdicios desperdicios = new ConsumoDesperdicios();
                desperdicios.setConsumo(consumo);
                desperdicios.setTipoResiduo("No clasificado"); // Valor por defecto, debe recibirse en la petición
                desperdicios.setMetodoEliminacion("Desconocido"); // Valor por defecto, debe recibirse en la petición
                consumoDesperdiciosRepository.save(desperdicios);
                break;

            default:
                throw new RuntimeException("Tipo de recurso no válido: " + consumo.getTipoRecurso());
        }

        return consumo;
    }

    @DeleteMapping("/{id}")
    public String borrarConsumo(@PathVariable Long id) {
        Consumo consumo = consumoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el consumo con el ID: " + id));

        switch (consumo.getTipoRecurso().toLowerCase()) {
            case "energía":
                consumoEnergiaRepository.deleteById(id);
                break;
            case "agua":
                consumoAguaRepository.deleteById(id);
                break;
            case "gas":
                consumoGasRepository.deleteById(id);
                break;
            case "desperdicios":
                consumoDesperdiciosRepository.deleteById(id);
                break;
        }

        consumoRepository.delete(consumo);
        return "El consumo con el ID: " + id + " fue eliminado correctamente";
    }
}