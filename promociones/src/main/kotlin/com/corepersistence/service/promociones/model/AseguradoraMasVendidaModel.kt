package com.corepersistence.service.promociones.model

import java.sql.Date
import javax.persistence.*


@Entity
@Table(name = "\"aseguradoraMasVendida\"")
data class AseguradoraMasVendidaModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id:Long = 0,

    @Column(name = "aseguradora")
    var aseguradora:String = "",

    @Column(name = "\"fechaExpiracion\"")
    var fechaExpiracion: Date? = null,

    @Column(name = "\"fechaInicio\"")
    var fechaInicio: Date? = Date( java.util.Date().time ),

    @Column(name = "activo")
    var activo:Int = 1
)