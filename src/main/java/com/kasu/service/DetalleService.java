package com.kasu.service;

import com.kasu.domain.Detalle;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Detalle}.
 */
public interface DetalleService {

    /**
     * Save a detalle.
     *
     * @param detalle the entity to save.
     * @return the persisted entity.
     */
    Detalle save(Detalle detalle);

    /**
     * Get all the detalles.
     *
     * @return the list of entities.
     */
    List<Detalle> findAll();


    /**
     * Get the "id" detalle.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Detalle> findOne(Long id);

    /**
     * Delete the "id" detalle.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
