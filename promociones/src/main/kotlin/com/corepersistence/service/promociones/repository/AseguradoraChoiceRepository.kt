package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.AseguradoraChoiceModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AseguradoraChoiceRepository:JpaRepository<AseguradoraChoiceModel,Long> {
}