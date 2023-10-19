package com.corepersistence.service.promociones.model

import org.hibernate.annotations.CollectionId
import java.sql.Date

import javax.persistence.*

@Entity
@Table(name = "\"aseguradoraRecomendada\"")

data class AseguradoraRecomendadaModel(
    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id:Int = 0,

    @Column(name="aseguradora")
        var aseguradora:String = "",

    @Column(name ="\"fechaExpiracion\"" )

    var fechaExpiracion: String? = "",

    @Column(name ="\"fechaInicio\"")
    var fechaInicio: String? = "",

    @Column(name="activo")
    var activo:Int = 1
)
