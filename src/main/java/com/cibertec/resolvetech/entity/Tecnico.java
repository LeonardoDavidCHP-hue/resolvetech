package com.cibertec.resolvetech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tecnico")
@Getter
@Setter
@NoArgsConstructor
public class Tecnico extends Usuario{

    @Column(nullable = false, length = 50)
    private String especialidad;

    @Column(length = 20)
    private String telefono;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sede")
    private Sede sede;

    public Tecnico(String nombre, String password, String rol, String especialidad, String telefono, Sede sede) {
        super(nombre, password, rol);
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.sede = sede;
    }
}
