package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.AseguradorasBD
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface AseguradoraRepository :JpaRepository<AseguradorasBD,Long>{

    fun findByName(name: String?):Optional<AseguradorasBD>
}