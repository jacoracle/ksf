package com.kasu.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Propiedad.
 */
@Entity
@Table(name = "propiedad")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Propiedad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "imagen")
    private String imagen;

    @Column(name = "geo_ref")
    private String geoRef;

    @Column(name = "precio", precision = 21, scale = 2)
    private BigDecimal precio;

    @OneToOne
    @JoinColumn(unique = true)
    private Direccion direccion;

    @OneToOne
    @JoinColumn(unique = true)
    private Detalle detalle;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Propiedad nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Propiedad descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public Propiedad tipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getImagen() {
        return imagen;
    }

    public Propiedad imagen(String imagen) {
        this.imagen = imagen;
        return this;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getGeoRef() {
        return geoRef;
    }

    public Propiedad geoRef(String geoRef) {
        this.geoRef = geoRef;
        return this;
    }

    public void setGeoRef(String geoRef) {
        this.geoRef = geoRef;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public Propiedad precio(BigDecimal precio) {
        this.precio = precio;
        return this;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Propiedad direccion(Direccion direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Detalle getDetalle() {
        return detalle;
    }

    public Propiedad detalle(Detalle detalle) {
        this.detalle = detalle;
        return this;
    }

    public void setDetalle(Detalle detalle) {
        this.detalle = detalle;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Propiedad)) {
            return false;
        }
        return id != null && id.equals(((Propiedad) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Propiedad{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", tipo='" + getTipo() + "'" +
            ", imagen='" + getImagen() + "'" +
            ", geoRef='" + getGeoRef() + "'" +
            ", precio=" + getPrecio() +
            "}";
    }
}
