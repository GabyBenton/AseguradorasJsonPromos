package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.PromocionesBD
import com.corepersistence.service.promociones.model.ResponseAseguradoraModelo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.swing.text.html.Option

@Repository
interface PromosRepository:JpaRepository<PromocionesBD,Long> {
    fun getAllBy(): Optional<List<PromocionesBD>>




}