package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.TipoBD
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TipoRepository:JpaRepository<TipoBD,Long> {
    fun findByType(type:String?):Optional<TipoBD>
}