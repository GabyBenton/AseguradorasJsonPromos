package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.AseguradoraMasVendidaModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AseguradoraMasVendidaRepository: JpaRepository<AseguradoraMasVendidaModel,Long> {
}