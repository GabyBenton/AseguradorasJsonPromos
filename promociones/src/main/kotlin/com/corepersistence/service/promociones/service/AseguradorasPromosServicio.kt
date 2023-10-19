package com.corepersistence.service.promociones.service

import com.corepersistence.service.promociones.model.*
import com.corepersistence.service.promociones.repository.AseguradoraRepository
import com.corepersistence.service.promociones.repository.PromosRepository
import com.corepersistence.service.promociones.repository.TipoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets

@Service
class AseguradorasPromosServicio {
    @Autowired
    lateinit var aseguradoraRepository: AseguradoraRepository
    @Autowired
    lateinit var tipoRepository: TipoRepository
    @Autowired
    lateinit var promosRepository: PromosRepository
    /*@Autowired
    private lateinit var tipoRepository: TipoRepository*/
    /*@Autowired
    lateinit var promocionesRepository : PromocionesRepository
    @Autowired
    lateinit var aseguradoraPromoRepository: AseguradoraPromoRepository           */

    private val objectMapper= ObjectMapper()
    val staticDataResource = ClassPathResource("data.json")
    val staticDataString: String = IOUtils.toString(staticDataResource.inputStream, StandardCharsets.UTF_8)


    fun imprimirDatitos():Json?{

        val jsonData = objectMapper.readValue(staticDataString, Json::class.java)
        println(jsonData)
        var info = jsonData.insurers?.get(0)?.name
        println(info)
        return jsonData
    }
    fun agregar (){
        val jsonData = objectMapper.readValue(staticDataString, Json::class.java)
        println(jsonData)
        var tipitos: HashSet<String> = HashSet()
///--------Tipos-----------------
        for (insurers in jsonData.insurers!!){
            for (promotions in insurers.promotions!!){
                    var tipo = promotions.type

                    tipitos.add(tipo!!)
                    //tipoRepository.save(tipos)

            }
        }
        tipitos.forEach { elemento ->
            //println(elemento)
            var tipos: TipoBD =  TipoBD( null,elemento!!)
            tipoRepository.save(tipos)
            //println("Este es e id: $idTipo y su $elemento")
            //idTipo++
        }

///--------Aseguradoras-----------------
        for (insurers in jsonData.insurers){
            var name = insurers.name
            var aseguradora : AseguradorasBD = AseguradorasBD(null,name!!)
            aseguradoraRepository.save(aseguradora)

            //println("Este es el: $name y este es el $idA")
            //idA++
            //aseguradoraPromoRepository.save(insurers)
        }

///--------PROMOCIONES-----------------
        // Promo: Long=1
        for (insurers in jsonData.insurers){
            for(promotions in insurers.promotions!!){
                var nombreA = aseguradoraRepository.findByName(insurers.name)
                var nombreT = tipoRepository.findByType(promotions.type)

                if (nombreA.isPresent){
                    var idA= nombreA.get().idAseguradoras
                    var idT = nombreT.get().idTipo
                    //var promociones = insurers.promotions
                    println(promotions)
                    //var type = promotions.type
                    var dto = promotions.dto
                    var msi = promotions.msi
                    var label = promotions.label
                    var urlImg = promotions.urlImg
                    var isSpecial = promotions.isSpecial
                    var extraMsg = promotions.extraMsg
                    var urlImgEcommerce = promotions.urlImgEcommerce
                    var nameAseguradora = insurers.name
                    var Promociones: PromocionesBD = PromocionesBD(null,idT,
                        idA,dto,msi,label,isSpecial,urlImg,extraMsg,urlImgEcommerce)

                    promosRepository.save(Promociones)
                    //Promo++

                }
                //promocionesRepository.save(promotions)
                //println(promociones)

            }
        }

    }
}