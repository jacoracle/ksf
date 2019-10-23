package com.kasu.service;

import com.kasu.domain.Propiedad;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Propiedad}.
 */
public interface PropiedadService {

    /**
     * Save a propiedad.
     *
     * @param propiedad the entity to save.
     * @return the persisted entity.
     */
    Propiedad save(Propiedad propiedad);

    /**
     * Get all the propiedads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Propiedad> findAll(Pageable pageable);


    /**
     * Get the "id" propiedad.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Propiedad> findOne(Long id);

    /**
     * Delete the "id" propiedad.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
