package com.kasu.web.rest;

import com.kasu.domain.Detalle;
import com.kasu.service.DetalleService;
import com.kasu.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kasu.domain.Detalle}.
 */
@RestController
@RequestMapping("/api")
public class DetalleResource {

    private final Logger log = LoggerFactory.getLogger(DetalleResource.class);

    private static final String ENTITY_NAME = "detalle";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetalleService detalleService;

    public DetalleResource(DetalleService detalleService) {
        this.detalleService = detalleService;
    }

    /**
     * {@code POST  /detalles} : Create a new detalle.
     *
     * @param detalle the detalle to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new detalle, or with status {@code 400 (Bad Request)} if the detalle has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/detalles")
    public ResponseEntity<Detalle> createDetalle(@RequestBody Detalle detalle) throws URISyntaxException {
        log.debug("REST request to save Detalle : {}", detalle);
        if (detalle.getId() != null) {
            throw new BadRequestAlertException("A new detalle cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Detalle result = detalleService.save(detalle);
        return ResponseEntity.created(new URI("/api/detalles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /detalles} : Updates an existing detalle.
     *
     * @param detalle the detalle to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated detalle,
     * or with status {@code 400 (Bad Request)} if the detalle is not valid,
     * or with status {@code 500 (Internal Server Error)} if the detalle couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/detalles")
    public ResponseEntity<Detalle> updateDetalle(@RequestBody Detalle detalle) throws URISyntaxException {
        log.debug("REST request to update Detalle : {}", detalle);
        if (detalle.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Detalle result = detalleService.save(detalle);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detalle.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /detalles} : get all the detalles.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of detalles in body.
     */
    @GetMapping("/detalles")
    public List<Detalle> getAllDetalles() {
        log.debug("REST request to get all Detalles");
        return detalleService.findAll();
    }

    /**
     * {@code GET  /detalles/:id} : get the "id" detalle.
     *
     * @param id the id of the detalle to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the detalle, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/detalles/{id}")
    public ResponseEntity<Detalle> getDetalle(@PathVariable Long id) {
        log.debug("REST request to get Detalle : {}", id);
        Optional<Detalle> detalle = detalleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detalle);
    }

    /**
     * {@code DELETE  /detalles/:id} : delete the "id" detalle.
     *
     * @param id the id of the detalle to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/detalles/{id}")
    public ResponseEntity<Void> deleteDetalle(@PathVariable Long id) {
        log.debug("REST request to delete Detalle : {}", id);
        detalleService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
