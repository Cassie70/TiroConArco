package com.ipn.mx.tiroconarcio.domain.repositories;

import com.ipn.mx.tiroconarcio.domain.models.Entrenamiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
}
