package com.corepersistence.service.promociones.repository

import com.corepersistence.service.promociones.model.PromocionesBD
import com.corepersistence.service.promociones.model.ResponseAseguradoraModelo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Repository
interface AseguradoraResponseRepository:JpaRepository<ResponseAseguradoraModelo,Long> {
    @Transactional
   /* @Query("""select promociones_tipo_seguro.type,
       promociones_aseguradoras.id_insurer ,
       promociones_aseguradoras.name,
       promociones.id_promocion,
       promociones.dto,
       promociones.msi,
       promociones.label,
       promociones.url_img,
       promociones.is_special,
       promociones.extra_msg,
       promociones.url_img_ecommerce
from produccion.operaciones.promociones_aseguradoras
inner join operaciones.promociones
    on promociones_aseguradoras.id_insurer = promociones.id_insurer
inner join operaciones.promociones_tipo_seguro
    on promociones_tipo_seguro.id_type = promociones.id_type""", nativeQuery = true)*/

    @Query("SELECT  T.type,A.id_insurer,A.name,P.id_Promocion, P.dto,P.msi,P.label,P.url_Img, P.is_Special,P.extra_Msg,P.url_Img_Ecommerce\n" +
            "            FROM promociones_aseguradoras AS A\n" +
            "            INNER JOIN promociones AS P\n" +
            "            ON A.id_insurer = P.id_insurer\n" +
            "            INNER JOIN promociones_tipo_seguro AS T\n" +
            "            ON P.id_type= T.id_type", nativeQuery = true)

    fun getAllBy(): Optional<List<ResponseAseguradoraModelo>>

    @Transactional
    @Query("SELECT  T.type,A.id_insurer,A.name,P.id_Promocion, P.dto,P.msi,P.label,P.url_Img, P.is_Special,P.extra_Msg,P.url_Img_Ecommerce\n" +
            "FROM promociones_aseguradoras AS A\n" +
            "INNER JOIN promociones AS P\n" +
            "ON A.id_insurer = P.id_insurer\n" +
            "INNER JOIN promociones_tipo_seguro AS T\n" +
            "ON P.id_type= T.id_type\n" +
            "WHERE A.name=:name\n", nativeQuery = true)
    fun findByName(name:String?):Optional<List<ResponseAseguradoraModelo>>

    @Transactional
    @Query("SELECT  T.type,A.id_insurer,A.name,P.id_Promocion, P.dto,P.msi,P.label,P.url_Img, P.is_Special,P.extra_Msg,P.url_Img_Ecommerce\n" +
            "FROM promociones_aseguradoras AS A\n" +
            "INNER JOIN promociones AS P\n" +
            "ON A.id_insurer = P.id_insurer\n" +
            "INNER JOIN promociones_tipo_seguro AS T\n" +
            "ON P.id_type= T.id_type\n" +
            "WHERE A.name=:name\n" +
            "AND T.type=:tipo", nativeQuery = true)
    fun findByAseguradorasAndType(name: String?,tipo:String?):Optional<ResponseAseguradoraModelo>
}