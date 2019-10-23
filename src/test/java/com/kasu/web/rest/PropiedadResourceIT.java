package com.kasu.web.rest;

import com.kasu.KasufyApp;
import com.kasu.domain.Propiedad;
import com.kasu.repository.PropiedadRepository;
import com.kasu.service.PropiedadService;
import com.kasu.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static com.kasu.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PropiedadResource} REST controller.
 */
@SpringBootTest(classes = KasufyApp.class)
public class PropiedadResourceIT {

    private static final String DEFAULT_NOMBRE = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPCION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPCION = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGEN = "AAAAAAAAAA";
    private static final String UPDATED_IMAGEN = "BBBBBBBBBB";

    private static final String DEFAULT_GEO_REF = "AAAAAAAAAA";
    private static final String UPDATED_GEO_REF = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRECIO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECIO = new BigDecimal(2);
    private static final BigDecimal SMALLER_PRECIO = new BigDecimal(1 - 1);

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private PropiedadService propiedadService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restPropiedadMockMvc;

    private Propiedad propiedad;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PropiedadResource propiedadResource = new PropiedadResource(propiedadService);
        this.restPropiedadMockMvc = MockMvcBuilders.standaloneSetup(propiedadResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Propiedad createEntity(EntityManager em) {
        Propiedad propiedad = new Propiedad()
            .nombre(DEFAULT_NOMBRE)
            .descripcion(DEFAULT_DESCRIPCION)
            .tipo(DEFAULT_TIPO)
            .imagen(DEFAULT_IMAGEN)
            .geoRef(DEFAULT_GEO_REF)
            .precio(DEFAULT_PRECIO);
        return propiedad;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Propiedad createUpdatedEntity(EntityManager em) {
        Propiedad propiedad = new Propiedad()
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .tipo(UPDATED_TIPO)
            .imagen(UPDATED_IMAGEN)
            .geoRef(UPDATED_GEO_REF)
            .precio(UPDATED_PRECIO);
        return propiedad;
    }

    @BeforeEach
    public void initTest() {
        propiedad = createEntity(em);
    }

    @Test
    @Transactional
    public void createPropiedad() throws Exception {
        int databaseSizeBeforeCreate = propiedadRepository.findAll().size();

        // Create the Propiedad
        restPropiedadMockMvc.perform(post("/api/propiedads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propiedad)))
            .andExpect(status().isCreated());

        // Validate the Propiedad in the database
        List<Propiedad> propiedadList = propiedadRepository.findAll();
        assertThat(propiedadList).hasSize(databaseSizeBeforeCreate + 1);
        Propiedad testPropiedad = propiedadList.get(propiedadList.size() - 1);
        assertThat(testPropiedad.getNombre()).isEqualTo(DEFAULT_NOMBRE);
        assertThat(testPropiedad.getDescripcion()).isEqualTo(DEFAULT_DESCRIPCION);
        assertThat(testPropiedad.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testPropiedad.getImagen()).isEqualTo(DEFAULT_IMAGEN);
        assertThat(testPropiedad.getGeoRef()).isEqualTo(DEFAULT_GEO_REF);
        assertThat(testPropiedad.getPrecio()).isEqualTo(DEFAULT_PRECIO);
    }

    @Test
    @Transactional
    public void createPropiedadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = propiedadRepository.findAll().size();

        // Create the Propiedad with an existing ID
        propiedad.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPropiedadMockMvc.perform(post("/api/propiedads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propiedad)))
            .andExpect(status().isBadRequest());

        // Validate the Propiedad in the database
        List<Propiedad> propiedadList = propiedadRepository.findAll();
        assertThat(propiedadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPropiedads() throws Exception {
        // Initialize the database
        propiedadRepository.saveAndFlush(propiedad);

        // Get all the propiedadList
        restPropiedadMockMvc.perform(get("/api/propiedads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(propiedad.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombre").value(hasItem(DEFAULT_NOMBRE.toString())))
            .andExpect(jsonPath("$.[*].descripcion").value(hasItem(DEFAULT_DESCRIPCION.toString())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].imagen").value(hasItem(DEFAULT_IMAGEN.toString())))
            .andExpect(jsonPath("$.[*].geoRef").value(hasItem(DEFAULT_GEO_REF.toString())))
            .andExpect(jsonPath("$.[*].precio").value(hasItem(DEFAULT_PRECIO.intValue())));
    }
    
    @Test
    @Transactional
    public void getPropiedad() throws Exception {
        // Initialize the database
        propiedadRepository.saveAndFlush(propiedad);

        // Get the propiedad
        restPropiedadMockMvc.perform(get("/api/propiedads/{id}", propiedad.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(propiedad.getId().intValue()))
            .andExpect(jsonPath("$.nombre").value(DEFAULT_NOMBRE.toString()))
            .andExpect(jsonPath("$.descripcion").value(DEFAULT_DESCRIPCION.toString()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.imagen").value(DEFAULT_IMAGEN.toString()))
            .andExpect(jsonPath("$.geoRef").value(DEFAULT_GEO_REF.toString()))
            .andExpect(jsonPath("$.precio").value(DEFAULT_PRECIO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPropiedad() throws Exception {
        // Get the propiedad
        restPropiedadMockMvc.perform(get("/api/propiedads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePropiedad() throws Exception {
        // Initialize the database
        propiedadService.save(propiedad);

        int databaseSizeBeforeUpdate = propiedadRepository.findAll().size();

        // Update the propiedad
        Propiedad updatedPropiedad = propiedadRepository.findById(propiedad.getId()).get();
        // Disconnect from session so that the updates on updatedPropiedad are not directly saved in db
        em.detach(updatedPropiedad);
        updatedPropiedad
            .nombre(UPDATED_NOMBRE)
            .descripcion(UPDATED_DESCRIPCION)
            .tipo(UPDATED_TIPO)
            .imagen(UPDATED_IMAGEN)
            .geoRef(UPDATED_GEO_REF)
            .precio(UPDATED_PRECIO);

        restPropiedadMockMvc.perform(put("/api/propiedads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPropiedad)))
            .andExpect(status().isOk());

        // Validate the Propiedad in the database
        List<Propiedad> propiedadList = propiedadRepository.findAll();
        assertThat(propiedadList).hasSize(databaseSizeBeforeUpdate);
        Propiedad testPropiedad = propiedadList.get(propiedadList.size() - 1);
        assertThat(testPropiedad.getNombre()).isEqualTo(UPDATED_NOMBRE);
        assertThat(testPropiedad.getDescripcion()).isEqualTo(UPDATED_DESCRIPCION);
        assertThat(testPropiedad.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testPropiedad.getImagen()).isEqualTo(UPDATED_IMAGEN);
        assertThat(testPropiedad.getGeoRef()).isEqualTo(UPDATED_GEO_REF);
        assertThat(testPropiedad.getPrecio()).isEqualTo(UPDATED_PRECIO);
    }

    @Test
    @Transactional
    public void updateNonExistingPropiedad() throws Exception {
        int databaseSizeBeforeUpdate = propiedadRepository.findAll().size();

        // Create the Propiedad

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPropiedadMockMvc.perform(put("/api/propiedads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(propiedad)))
            .andExpect(status().isBadRequest());

        // Validate the Propiedad in the database
        List<Propiedad> propiedadList = propiedadRepository.findAll();
        assertThat(propiedadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePropiedad() throws Exception {
        // Initialize the database
        propiedadService.save(propiedad);

        int databaseSizeBeforeDelete = propiedadRepository.findAll().size();

        // Delete the propiedad
        restPropiedadMockMvc.perform(delete("/api/propiedads/{id}", propiedad.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Propiedad> propiedadList = propiedadRepository.findAll();
        assertThat(propiedadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Propiedad.class);
        Propiedad propiedad1 = new Propiedad();
        propiedad1.setId(1L);
        Propiedad propiedad2 = new Propiedad();
        propiedad2.setId(propiedad1.getId());
        assertThat(propiedad1).isEqualTo(propiedad2);
        propiedad2.setId(2L);
        assertThat(propiedad1).isNotEqualTo(propiedad2);
        propiedad1.setId(null);
        assertThat(propiedad1).isNotEqualTo(propiedad2);
    }
}
