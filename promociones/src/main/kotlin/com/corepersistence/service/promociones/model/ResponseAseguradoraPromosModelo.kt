package com.corepersistence.service.promociones.model

import java.sql.Date
import javax.persistence.*

@Entity
data class ResponseAseguradoraPromosModelo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id:Int = 0,

    @Column(name = "aseguradora")
    var aseguradora:String = "",

    @Column(name = "\"fechaExpiracion\"")
    var fechaExpiracion: String? = "",

    @Column(name = "\"fechaInicio\"")
    var fechaInicio: String? = ""

)