package com.alianza.test.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "actividad", uniqueConstraints = @UniqueConstraint(name = "actividad_uk", columnNames = "nombre"))
@SQLDelete(sql = "UPDATE leccion SET eliminado = current_date WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "eliminado = to_date('09-09-1999','dd-mm-yyyy')")
public class Actividad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre",length = 100)
    private String nombre;

    @Column(name = "status",length = 1)
    private int status;

    @NotFound(action= NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({ org.hibernate.annotations.CascadeType.REFRESH })
    @JoinColumn(name = "empleado_asignado_id", columnDefinition = "int4 NULL")
    @OrderBy("nombre")
    private Empleado empleadoAsignado;

    @Column(name = "fechaCreacion")
    @Temporal(TemporalType.DATE)
    private Date fechaCreacion;

    @Column(name = "fechaPlaneadaFinalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaPlaneadaFinalizacion;

    @Column(name = "fechaFinalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechaFinalizacion;

    @Column(name = "eliminado")
    @Temporal(TemporalType.DATE)
    @Builder.Default
    private Date eliminado = new Date("1999/09/09");

    @Column(name = "diasRetraso")
    private int diasRetraso;

    /**
     *
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;
}
