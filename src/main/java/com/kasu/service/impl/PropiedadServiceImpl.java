package com.kasu.service.impl;

import com.kasu.service.PropiedadService;
import com.kasu.domain.Propiedad;
import com.kasu.repository.PropiedadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Propiedad}.
 */
@Service
@Transactional
public class PropiedadServiceImpl implements PropiedadService {

    private final Logger log = LoggerFactory.getLogger(PropiedadServiceImpl.class);

    private final PropiedadRepository propiedadRepository;

    public PropiedadServiceImpl(PropiedadRepository propiedadRepository) {
        this.propiedadRepository = propiedadRepository;
    }

    /**
     * Save a propiedad.
     *
     * @param propiedad the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Propiedad save(Propiedad propiedad) {
        log.debug("Request to save Propiedad : {}", propiedad);
        return propiedadRepository.save(propiedad);
    }

    /**
     * Get all the propiedads.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Propiedad> findAll(Pageable pageable) {
        log.debug("Request to get all Propiedads");
        return propiedadRepository.findAll(pageable);
    }


    /**
     * Get one propiedad by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Propiedad> findOne(Long id) {
        log.debug("Request to get Propiedad : {}", id);
        return propiedadRepository.findById(id);
    }

    /**
     * Delete the propiedad by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Propiedad : {}", id);
        propiedadRepository.deleteById(id);
    }
}
