package com.uce.wasitech.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uce.wasitech.Entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
