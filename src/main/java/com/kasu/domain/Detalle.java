package com.kasu.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Detalle.
 */
@Entity
@Table(name = "detalle")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Detalle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_recamara")
    private Integer numRecamara;

    @Column(name = "num_cocina")
    private Integer numCocina;

    @Column(name = "num_bano")
    private Integer numBano;

    @Column(name = "num_sala")
    private Integer numSala;

    @Column(name = "num_estudio")
    private Integer numEstudio;

    @Column(name = "num_garage")
    private Integer numGarage;

    @Column(name = "num_jardin")
    private Integer numJardin;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumRecamara() {
        return numRecamara;
    }

    public Detalle numRecamara(Integer numRecamara) {
        this.numRecamara = numRecamara;
        return this;
    }

    public void setNumRecamara(Integer numRecamara) {
        this.numRecamara = numRecamara;
    }

    public Integer getNumCocina() {
        return numCocina;
    }

    public Detalle numCocina(Integer numCocina) {
        this.numCocina = numCocina;
        return this;
    }

    public void setNumCocina(Integer numCocina) {
        this.numCocina = numCocina;
    }

    public Integer getNumBano() {
        return numBano;
    }

    public Detalle numBano(Integer numBano) {
        this.numBano = numBano;
        return this;
    }

    public void setNumBano(Integer numBano) {
        this.numBano = numBano;
    }

    public Integer getNumSala() {
        return numSala;
    }

    public Detalle numSala(Integer numSala) {
        this.numSala = numSala;
        return this;
    }

    public void setNumSala(Integer numSala) {
        this.numSala = numSala;
    }

    public Integer getNumEstudio() {
        return numEstudio;
    }

    public Detalle numEstudio(Integer numEstudio) {
        this.numEstudio = numEstudio;
        return this;
    }

    public void setNumEstudio(Integer numEstudio) {
        this.numEstudio = numEstudio;
    }

    public Integer getNumGarage() {
        return numGarage;
    }

    public Detalle numGarage(Integer numGarage) {
        this.numGarage = numGarage;
        return this;
    }

    public void setNumGarage(Integer numGarage) {
        this.numGarage = numGarage;
    }

    public Integer getNumJardin() {
        return numJardin;
    }

    public Detalle numJardin(Integer numJardin) {
        this.numJardin = numJardin;
        return this;
    }

    public void setNumJardin(Integer numJardin) {
        this.numJardin = numJardin;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Detalle)) {
            return false;
        }
        return id != null && id.equals(((Detalle) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Detalle{" +
            "id=" + getId() +
            ", numRecamara=" + getNumRecamara() +
            ", numCocina=" + getNumCocina() +
            ", numBano=" + getNumBano() +
            ", numSala=" + getNumSala() +
            ", numEstudio=" + getNumEstudio() +
            ", numGarage=" + getNumGarage() +
            ", numJardin=" + getNumJardin() +
            "}";
    }
}
