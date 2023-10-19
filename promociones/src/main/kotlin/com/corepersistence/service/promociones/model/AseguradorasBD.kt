package com.corepersistence.service.promociones.model

import javax.persistence.*

@Entity
@Table(name="promociones_aseguradoras")
data class AseguradorasBD(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id_insurer", unique=true, nullable=false)
    val idAseguradoras: Long?,

    @Column(name="name", unique=true, nullable=false)
    val name: String
)