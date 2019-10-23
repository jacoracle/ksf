package com.kasu.web.rest;

import com.kasu.KasufyApp;
import com.kasu.domain.Detalle;
import com.kasu.repository.DetalleRepository;
import com.kasu.service.DetalleService;
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
import java.util.List;

import static com.kasu.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DetalleResource} REST controller.
 */
@SpringBootTest(classes = KasufyApp.class)
public class DetalleResourceIT {

    private static final Integer DEFAULT_NUM_RECAMARA = 1;
    private static final Integer UPDATED_NUM_RECAMARA = 2;
    private static final Integer SMALLER_NUM_RECAMARA = 1 - 1;

    private static final Integer DEFAULT_NUM_COCINA = 1;
    private static final Integer UPDATED_NUM_COCINA = 2;
    private static final Integer SMALLER_NUM_COCINA = 1 - 1;

    private static final Integer DEFAULT_NUM_BANO = 1;
    private static final Integer UPDATED_NUM_BANO = 2;
    private static final Integer SMALLER_NUM_BANO = 1 - 1;

    private static final Integer DEFAULT_NUM_SALA = 1;
    private static final Integer UPDATED_NUM_SALA = 2;
    private static final Integer SMALLER_NUM_SALA = 1 - 1;

    private static final Integer DEFAULT_NUM_ESTUDIO = 1;
    private static final Integer UPDATED_NUM_ESTUDIO = 2;
    private static final Integer SMALLER_NUM_ESTUDIO = 1 - 1;

    private static final Integer DEFAULT_NUM_GARAGE = 1;
    private static final Integer UPDATED_NUM_GARAGE = 2;
    private static final Integer SMALLER_NUM_GARAGE = 1 - 1;

    private static final Integer DEFAULT_NUM_JARDIN = 1;
    private static final Integer UPDATED_NUM_JARDIN = 2;
    private static final Integer SMALLER_NUM_JARDIN = 1 - 1;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private DetalleService detalleService;

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

    private MockMvc restDetalleMockMvc;

    private Detalle detalle;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DetalleResource detalleResource = new DetalleResource(detalleService);
        this.restDetalleMockMvc = MockMvcBuilders.standaloneSetup(detalleResource)
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
    public static Detalle createEntity(EntityManager em) {
        Detalle detalle = new Detalle()
            .numRecamara(DEFAULT_NUM_RECAMARA)
            .numCocina(DEFAULT_NUM_COCINA)
            .numBano(DEFAULT_NUM_BANO)
            .numSala(DEFAULT_NUM_SALA)
            .numEstudio(DEFAULT_NUM_ESTUDIO)
            .numGarage(DEFAULT_NUM_GARAGE)
            .numJardin(DEFAULT_NUM_JARDIN);
        return detalle;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Detalle createUpdatedEntity(EntityManager em) {
        Detalle detalle = new Detalle()
            .numRecamara(UPDATED_NUM_RECAMARA)
            .numCocina(UPDATED_NUM_COCINA)
            .numBano(UPDATED_NUM_BANO)
            .numSala(UPDATED_NUM_SALA)
            .numEstudio(UPDATED_NUM_ESTUDIO)
            .numGarage(UPDATED_NUM_GARAGE)
            .numJardin(UPDATED_NUM_JARDIN);
        return detalle;
    }

    @BeforeEach
    public void initTest() {
        detalle = createEntity(em);
    }

    @Test
    @Transactional
    public void createDetalle() throws Exception {
        int databaseSizeBeforeCreate = detalleRepository.findAll().size();

        // Create the Detalle
        restDetalleMockMvc.perform(post("/api/detalles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalle)))
            .andExpect(status().isCreated());

        // Validate the Detalle in the database
        List<Detalle> detalleList = detalleRepository.findAll();
        assertThat(detalleList).hasSize(databaseSizeBeforeCreate + 1);
        Detalle testDetalle = detalleList.get(detalleList.size() - 1);
        assertThat(testDetalle.getNumRecamara()).isEqualTo(DEFAULT_NUM_RECAMARA);
        assertThat(testDetalle.getNumCocina()).isEqualTo(DEFAULT_NUM_COCINA);
        assertThat(testDetalle.getNumBano()).isEqualTo(DEFAULT_NUM_BANO);
        assertThat(testDetalle.getNumSala()).isEqualTo(DEFAULT_NUM_SALA);
        assertThat(testDetalle.getNumEstudio()).isEqualTo(DEFAULT_NUM_ESTUDIO);
        assertThat(testDetalle.getNumGarage()).isEqualTo(DEFAULT_NUM_GARAGE);
        assertThat(testDetalle.getNumJardin()).isEqualTo(DEFAULT_NUM_JARDIN);
    }

    @Test
    @Transactional
    public void createDetalleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = detalleRepository.findAll().size();

        // Create the Detalle with an existing ID
        detalle.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDetalleMockMvc.perform(post("/api/detalles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalle)))
            .andExpect(status().isBadRequest());

        // Validate the Detalle in the database
        List<Detalle> detalleList = detalleRepository.findAll();
        assertThat(detalleList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDetalles() throws Exception {
        // Initialize the database
        detalleRepository.saveAndFlush(detalle);

        // Get all the detalleList
        restDetalleMockMvc.perform(get("/api/detalles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(detalle.getId().intValue())))
            .andExpect(jsonPath("$.[*].numRecamara").value(hasItem(DEFAULT_NUM_RECAMARA)))
            .andExpect(jsonPath("$.[*].numCocina").value(hasItem(DEFAULT_NUM_COCINA)))
            .andExpect(jsonPath("$.[*].numBano").value(hasItem(DEFAULT_NUM_BANO)))
            .andExpect(jsonPath("$.[*].numSala").value(hasItem(DEFAULT_NUM_SALA)))
            .andExpect(jsonPath("$.[*].numEstudio").value(hasItem(DEFAULT_NUM_ESTUDIO)))
            .andExpect(jsonPath("$.[*].numGarage").value(hasItem(DEFAULT_NUM_GARAGE)))
            .andExpect(jsonPath("$.[*].numJardin").value(hasItem(DEFAULT_NUM_JARDIN)));
    }
    
    @Test
    @Transactional
    public void getDetalle() throws Exception {
        // Initialize the database
        detalleRepository.saveAndFlush(detalle);

        // Get the detalle
        restDetalleMockMvc.perform(get("/api/detalles/{id}", detalle.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(detalle.getId().intValue()))
            .andExpect(jsonPath("$.numRecamara").value(DEFAULT_NUM_RECAMARA))
            .andExpect(jsonPath("$.numCocina").value(DEFAULT_NUM_COCINA))
            .andExpect(jsonPath("$.numBano").value(DEFAULT_NUM_BANO))
            .andExpect(jsonPath("$.numSala").value(DEFAULT_NUM_SALA))
            .andExpect(jsonPath("$.numEstudio").value(DEFAULT_NUM_ESTUDIO))
            .andExpect(jsonPath("$.numGarage").value(DEFAULT_NUM_GARAGE))
            .andExpect(jsonPath("$.numJardin").value(DEFAULT_NUM_JARDIN));
    }

    @Test
    @Transactional
    public void getNonExistingDetalle() throws Exception {
        // Get the detalle
        restDetalleMockMvc.perform(get("/api/detalles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDetalle() throws Exception {
        // Initialize the database
        detalleService.save(detalle);

        int databaseSizeBeforeUpdate = detalleRepository.findAll().size();

        // Update the detalle
        Detalle updatedDetalle = detalleRepository.findById(detalle.getId()).get();
        // Disconnect from session so that the updates on updatedDetalle are not directly saved in db
        em.detach(updatedDetalle);
        updatedDetalle
            .numRecamara(UPDATED_NUM_RECAMARA)
            .numCocina(UPDATED_NUM_COCINA)
            .numBano(UPDATED_NUM_BANO)
            .numSala(UPDATED_NUM_SALA)
            .numEstudio(UPDATED_NUM_ESTUDIO)
            .numGarage(UPDATED_NUM_GARAGE)
            .numJardin(UPDATED_NUM_JARDIN);

        restDetalleMockMvc.perform(put("/api/detalles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDetalle)))
            .andExpect(status().isOk());

        // Validate the Detalle in the database
        List<Detalle> detalleList = detalleRepository.findAll();
        assertThat(detalleList).hasSize(databaseSizeBeforeUpdate);
        Detalle testDetalle = detalleList.get(detalleList.size() - 1);
        assertThat(testDetalle.getNumRecamara()).isEqualTo(UPDATED_NUM_RECAMARA);
        assertThat(testDetalle.getNumCocina()).isEqualTo(UPDATED_NUM_COCINA);
        assertThat(testDetalle.getNumBano()).isEqualTo(UPDATED_NUM_BANO);
        assertThat(testDetalle.getNumSala()).isEqualTo(UPDATED_NUM_SALA);
        assertThat(testDetalle.getNumEstudio()).isEqualTo(UPDATED_NUM_ESTUDIO);
        assertThat(testDetalle.getNumGarage()).isEqualTo(UPDATED_NUM_GARAGE);
        assertThat(testDetalle.getNumJardin()).isEqualTo(UPDATED_NUM_JARDIN);
    }

    @Test
    @Transactional
    public void updateNonExistingDetalle() throws Exception {
        int databaseSizeBeforeUpdate = detalleRepository.findAll().size();

        // Create the Detalle

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDetalleMockMvc.perform(put("/api/detalles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(detalle)))
            .andExpect(status().isBadRequest());

        // Validate the Detalle in the database
        List<Detalle> detalleList = detalleRepository.findAll();
        assertThat(detalleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDetalle() throws Exception {
        // Initialize the database
        detalleService.save(detalle);

        int databaseSizeBeforeDelete = detalleRepository.findAll().size();

        // Delete the detalle
        restDetalleMockMvc.perform(delete("/api/detalles/{id}", detalle.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Detalle> detalleList = detalleRepository.findAll();
        assertThat(detalleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Detalle.class);
        Detalle detalle1 = new Detalle();
        detalle1.setId(1L);
        Detalle detalle2 = new Detalle();
        detalle2.setId(detalle1.getId());
        assertThat(detalle1).isEqualTo(detalle2);
        detalle2.setId(2L);
        assertThat(detalle1).isNotEqualTo(detalle2);
        detalle1.setId(null);
        assertThat(detalle1).isNotEqualTo(detalle2);
    }
}
