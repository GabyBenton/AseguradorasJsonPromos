package com.corepersistence.service.promociones.model

import javax.persistence.*

@Entity
@Table(name="promociones")
data class PromocionesBD(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name="id_promocion", unique=true, nullable=false)
        var idPromocion: Long?=null,

        @Column(name="id_type", nullable=false)
        var type: Long?=null,

        @Column(name="id_insurer", nullable=false)
        var aseguradoras: Long?=null,

        var dto: String?=null,

        var msi: String?=null,

        var label: String?=null,

        var isSpecial: Boolean?=null,

        var urlImg: String?=null,

        var extraMsg: String?=null,
        var urlImgEcommerce:String? =null



)