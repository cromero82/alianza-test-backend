package com.alianza.test.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "cliente", uniqueConstraints = @UniqueConstraint(name = "cliente_uk", columnNames = "shared_key"))
@SQLDelete(sql = "UPDATE leccion SET eliminado = current_date WHERE id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "eliminado = to_date('09-09-1999','dd-mm-yyyy')")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "shared_key",length = 100)
    private String sharedKey;

    @Column(name = "nombre",length = 100)
    private String nombre;

    @Column(name = "telefono",length = 15)
    private String telefono;

    @Column(name = "correo",length = 100)
    private String correo;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Column(name = "eliminado")
    @Builder.Default
    private Date eliminado = new Date("1999/09/09");


    /**
     *
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final long serialVersionUID = 1L;
}
