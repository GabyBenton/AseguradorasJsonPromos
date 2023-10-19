package com.corepersistence.service.promociones.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
@Entity
data class ResponseAseguradoraModelo (

    val type: String?= null,

    val id_insurer: Long?=null,

    val name :String?=null,
    @Id
    val id_promocion:Long?,

    val dto: String?=null,

    val msi: String?=null,

    val label: String?=null,

    val url_Img: String?=null,

    val is_Special: Boolean?=null,

    val extra_Msg: String?=null,

    val url_Img_Ecommerce: String?=null


)