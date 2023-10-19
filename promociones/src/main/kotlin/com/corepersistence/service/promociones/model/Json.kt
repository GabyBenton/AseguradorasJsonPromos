package com.corepersistence.service.promociones.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class Json @JsonCreator constructor (

        @JsonProperty("success")val success : Boolean?,
        @JsonProperty("results")val results: String?,
        @JsonProperty("aseguradoraRecomendada") val aseguradoraRcomendada :List<ResponseAseguradoraPromosModelo>?,
        @JsonProperty("aseguradoraChoise") val aseguradoraChoise :ResponseAseguradoraPromosModelo?,
        @JsonProperty("aseguradoraMasVendida") val aseguradoraMasVendida :ResponseAseguradoraPromosModelo?,
        @JsonProperty("insurers") val insurers : List<InsurersJson>?


)
