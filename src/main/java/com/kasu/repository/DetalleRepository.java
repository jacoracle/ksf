package com.kasu.repository;

import com.kasu.domain.Detalle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Detalle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {

}
