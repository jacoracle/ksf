package com.kasu.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Direccion.
 */
@Entity
@Table(name = "direccion")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "calle")
    private String calle;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "munucipio")
    private String munucipio;

    @Column(name = "estado")
    private String estado;

    @Column(name = "codigo_postl")
    private Integer codigoPostl;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public Direccion calle(String calle) {
        this.calle = calle;
        return this;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public Direccion colonia(String colonia) {
        this.colonia = colonia;
        return this;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getMunucipio() {
        return munucipio;
    }

    public Direccion munucipio(String munucipio) {
        this.munucipio = munucipio;
        return this;
    }

    public void setMunucipio(String munucipio) {
        this.munucipio = munucipio;
    }

    public String getEstado() {
        return estado;
    }

    public Direccion estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getCodigoPostl() {
        return codigoPostl;
    }

    public Direccion codigoPostl(Integer codigoPostl) {
        this.codigoPostl = codigoPostl;
        return this;
    }

    public void setCodigoPostl(Integer codigoPostl) {
        this.codigoPostl = codigoPostl;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Direccion)) {
            return false;
        }
        return id != null && id.equals(((Direccion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Direccion{" +
            "id=" + getId() +
            ", calle='" + getCalle() + "'" +
            ", colonia='" + getColonia() + "'" +
            ", munucipio='" + getMunucipio() + "'" +
            ", estado='" + getEstado() + "'" +
            ", codigoPostl=" + getCodigoPostl() +
            "}";
    }
}
