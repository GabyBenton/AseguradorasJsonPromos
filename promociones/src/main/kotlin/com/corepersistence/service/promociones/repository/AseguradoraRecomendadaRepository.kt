package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.AseguradoraRecomendadaModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AseguradoraRecomendadaRepository:JpaRepository<AseguradoraRecomendadaModel,Int> {
}