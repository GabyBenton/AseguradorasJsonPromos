package com.corepersistence.service.promociones.model

data class ListaAseguradoraResponseModelo(
    val name: String?= null,
    val idAseguradora: Long?= null,
    val listaAseguradoras: List<ResponseAseguradoraModelo>


    )