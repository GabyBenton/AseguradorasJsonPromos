package com.corepersistence.service.promociones.model

import javax.persistence.*

@Entity
@Table(name="promociones_tipo_seguro")
data class TipoBD(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id_type", unique=true, nullable=false)
    val idTipo: Long?,

    @Column(name="type", unique=true, nullable=false)
    val type: String
)