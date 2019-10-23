package com.kasu.web.rest;

import com.kasu.domain.Propiedad;
import com.kasu.service.PropiedadService;
import com.kasu.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.kasu.domain.Propiedad}.
 */
@RestController
@RequestMapping("/api")
public class PropiedadResource {

    private final Logger log = LoggerFactory.getLogger(PropiedadResource.class);

    private static final String ENTITY_NAME = "propiedad";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PropiedadService propiedadService;

    public PropiedadResource(PropiedadService propiedadService) {
        this.propiedadService = propiedadService;
    }

    /**
     * {@code POST  /propiedads} : Create a new propiedad.
     *
     * @param propiedad the propiedad to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new propiedad, or with status {@code 400 (Bad Request)} if the propiedad has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/propiedads")
    public ResponseEntity<Propiedad> createPropiedad(@RequestBody Propiedad propiedad) throws URISyntaxException {
        log.debug("REST request to save Propiedad : {}", propiedad);
        if (propiedad.getId() != null) {
            throw new BadRequestAlertException("A new propiedad cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Propiedad result = propiedadService.save(propiedad);
        return ResponseEntity.created(new URI("/api/propiedads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /propiedads} : Updates an existing propiedad.
     *
     * @param propiedad the propiedad to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated propiedad,
     * or with status {@code 400 (Bad Request)} if the propiedad is not valid,
     * or with status {@code 500 (Internal Server Error)} if the propiedad couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/propiedads")
    public ResponseEntity<Propiedad> updatePropiedad(@RequestBody Propiedad propiedad) throws URISyntaxException {
        log.debug("REST request to update Propiedad : {}", propiedad);
        if (propiedad.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Propiedad result = propiedadService.save(propiedad);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, propiedad.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /propiedads} : get all the propiedads.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of propiedads in body.
     */
    @GetMapping("/propiedads")
    public ResponseEntity<List<Propiedad>> getAllPropiedads(Pageable pageable) {
        log.debug("REST request to get a page of Propiedads");
        Page<Propiedad> page = propiedadService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /propiedads/:id} : get the "id" propiedad.
     *
     * @param id the id of the propiedad to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the propiedad, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/propiedads/{id}")
    public ResponseEntity<Propiedad> getPropiedad(@PathVariable Long id) {
        log.debug("REST request to get Propiedad : {}", id);
        Optional<Propiedad> propiedad = propiedadService.findOne(id);
        return ResponseUtil.wrapOrNotFound(propiedad);
    }

    /**
     * {@code DELETE  /propiedads/:id} : delete the "id" propiedad.
     *
     * @param id the id of the propiedad to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/propiedads/{id}")
    public ResponseEntity<Void> deletePropiedad(@PathVariable Long id) {
        log.debug("REST request to delete Propiedad : {}", id);
        propiedadService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
