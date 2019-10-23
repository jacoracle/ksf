package com.kasu.repository;

import com.kasu.domain.Propiedad;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Propiedad entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {

}
