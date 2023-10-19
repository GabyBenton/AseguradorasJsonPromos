package com.corepersistence.service.promociones.service

import com.corepersistence.service.promociones.exceptions.promosExceptions
import com.corepersistence.service.promociones.model.*
import com.corepersistence.service.promociones.repository.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

@Service
class ResponseAseguradoraService {
    @Autowired
    lateinit var aseguradoraResponseRepository: AseguradoraResponseRepository
    @Autowired
    lateinit var aseguradoraRepository: AseguradoraRepository
    @Autowired
    lateinit var promosRepository: PromosRepository
    @Autowired
    lateinit var tipoRepository: TipoRepository

    //Repositorios AseguradoraMasVendida
    @Autowired
    lateinit var aseguradoraMasVendidaRepository: AseguradoraMasVendidaRepository
    //Repositorios AseguradoraRecomendada
    @Autowired
    lateinit var aseguradoraRecomendadaRepository: AseguradoraRecomendadaRepository
    //Repositorios AseguradoraChoice
    @Autowired
    lateinit var aseguradoraChoiceRepository: AseguradoraChoiceRepository


    fun getDatos(): MutableList<Any> {
        var info =aseguradoraResponseRepository.getAllBy()
        var listaAseguradoras = aseguradoraRepository.findAll()
        var listaJsonPromo : MutableList<PromotionJson> = mutableListOf()
        var listaJsonAseguradoras : MutableList<Any> = mutableListOf()

        if(info.isPresent){
            var promociones= info.get()
            for (aseguradoras in listaAseguradoras) {
                for (l in promociones) {
                    if(aseguradoras.name==l.name) {
                        println(l)
                        var type = l.type
                        var dto = l.dto
                        var msi = l.msi
                        var label = l.label
                        var urlImg = l.url_Img
                        var isSpecial = l.is_Special
                        var extraMsg = l.extra_Msg
                        var urlImgEcommerce = l.url_Img_Ecommerce
                        var Promos: PromotionJson = PromotionJson(type, dto, msi, label, urlImg, isSpecial, extraMsg,urlImgEcommerce)
                        listaJsonPromo.add(Promos)
                    }
                }
                var name = aseguradoras.name
                var _id = aseguradoras.idAseguradoras
                var Aseguradora = InsurersJson(listaJsonPromo,_id.toString(),name)
                listaJsonAseguradoras.add(Aseguradora)
                listaJsonPromo= mutableListOf()
            }
            return listaJsonAseguradoras
        } else return throw promosExceptions(HttpStatus.NOT_FOUND,"No se pudieron encontrar los datos")
    }

    fun getDatitos(): Json? {
        var info =aseguradoraResponseRepository.getAllBy()
        var listaAseguradoras = aseguradoraRepository.findAll()
        var listaJsonAseguradoras : MutableList<InsurersJson> = mutableListOf()
        var listaJson :MutableList<Json> = mutableListOf()
        //Codigo para aseguradoraRecomendada/aseguradoraMasVendida/ AseguradoraChoice

        var aseguradoraRecomendada= aseguradoraRecomendadaRepository.findAll()
        var json: Json? = null

        if(info.isPresent){
            var promociones= info.get()
            for (aseguradoras in listaAseguradoras) {
                var PromosPorAseguradora = promociones
                    .filter { p -> p.name==aseguradoras.name}
                    .map { p -> PromotionJson(
                        p.type,
                        p.dto,
                        p.msi,
                        p.label,
                        p.url_Img,
                        p.is_Special,
                        p.extra_Msg,
                        p.url_Img_Ecommerce)
                    }
                //println(PromosPorAseguradora)
                var _id = aseguradoras.idAseguradoras
                var name =aseguradoras.name
                var Aseguradora = InsurersJson(PromosPorAseguradora,_id.toString(),name)
                listaJsonAseguradoras.add(Aseguradora)

                //println(aseguradoraRecomendada)
                //println(infoAseguradoraRecomendada)
                //println(json)
                //println(listaJson)
            }
            var infoAseguradoraRecomendada = aseguradoraRecomendada
                .map { a -> ResponseAseguradoraPromosModelo(
                    a.id,
                    a.aseguradora,
                    a.fechaExpiracion,
                    a.fechaInicio)
                }
            json = Json(true,listaAseguradoras.size.toString(),infoAseguradoraRecomendada,null,null,listaJsonAseguradoras)
            listaJson.add(json)

        }
        return json
    }

    fun getPorName(@PathVariable("name") name:String?): MutableList<PromotionJson> {
        var encontrarName = aseguradoraResponseRepository.findByName(name?.uppercase())
       //var info =aseguradoraResponseRepository.getAllBy()
        var promociones= encontrarName.get()
        var PromosPorAseguradora: MutableList<PromotionJson> = mutableListOf()
        if (encontrarName.isPresent){
            for(promos in promociones){
                var type = promos.type
                var dto = promos.dto
                var msi = promos.msi
                var label = promos.label
                var urlImg = promos.url_Img
                var isSpecial = promos.is_Special
                var extraMsg = promos.extra_Msg
                var urlImgEcommerce = promos.url_Img_Ecommerce
                var PromosAseguradora:PromotionJson= PromotionJson(type,dto,msi,label,urlImg,isSpecial,extraMsg,urlImgEcommerce)
                PromosPorAseguradora.add(PromosAseguradora)
            }
            //println(PromosPorAseguradora)
        }

        return PromosPorAseguradora
    }

    fun agregarPromos(@PathVariable("name") name:String?, @RequestBody promotionJson: PromotionJson): PromocionesBD {
        var encontrarName = aseguradoraRepository.findByName(name?.uppercase())
        var encontrarTipo = tipoRepository.findByType(promotionJson.type)
        var promocion=aseguradoraResponseRepository.findByAseguradorasAndType(name,promotionJson.type)
        if(promocion.isEmpty) {
            //var idPromo = promosRepository.findById()
            //println(promotionJson)
            //println(encontrarName)
            //println(encontrarTipo)
            //var encontrarId = promosRepository.findById(PromocionesBD)

            var promocionesBD = PromocionesBD()
            promocionesBD.aseguradoras = encontrarName.get().idAseguradoras
            promocionesBD.type = encontrarTipo.get().idTipo
            promocionesBD.dto = promotionJson.dto
            promocionesBD.msi = promotionJson.msi
            promocionesBD.label = promotionJson.label
            promocionesBD.urlImg = promotionJson.urlImg
            promocionesBD.isSpecial = promotionJson.isSpecial
            promocionesBD.extraMsg = promotionJson.extraMsg
            promocionesBD.urlImgEcommerce = promotionJson.urlImgEcommerce
            //println("holaaaa")
            //println(promocionesBD)
            //println("holaaaa x2")
            promosRepository.save(promocionesBD)
            return promocionesBD
        }else return throw promosExceptions(HttpStatus.BAD_REQUEST,"Ya existe una promocion de ese tipo")

    }


    fun borrarPromos(@PathVariable("name")name:String?,
                     @PathVariable("tipo")tipo:String?): ResponseEntity<PromocionesBD> {
        var promocion=aseguradoraResponseRepository.findByAseguradorasAndType(name,tipo).get().id_promocion
        var promocionExists = aseguradoraResponseRepository.findByAseguradorasAndType(name,tipo)
        if (promocionExists.isPresent) {
            val buscarid = promosRepository.findById(promocion!!).get()
            val borrarpromo=promosRepository.deleteById(promocion!!)
            return ResponseEntity(buscarid,HttpStatus.OK)
        }else return throw promosExceptions(HttpStatus.NOT_MODIFIED,"No se pudieron borrar los datos")

    }
/*------------------------UPDATE-----------------------------------*/
    /*----UPDATE PROMOCION-----*/
    fun actualizarPromos(@PathVariable("name")name:String?,
                         @PathVariable("tipo")tipo:String?,@RequestBody promotionJson: PromotionJson): PromocionesBD {
        var promocion=aseguradoraResponseRepository.findByAseguradorasAndType(name,tipo)
        if(promocion.isPresent) {
            println(promocion)
            var promoGet = promocion.get()
            var encontrarTipo = tipoRepository.findByType(promotionJson.type)
            var promocionesBD = PromocionesBD()
            promocionesBD.idPromocion=promoGet.id_promocion
            if(promoGet.id_insurer!=null) promocionesBD.aseguradoras = promoGet.id_insurer
            if(encontrarTipo.get().idTipo!=null) promocionesBD.type = encontrarTipo.get().idTipo
            if(promotionJson.dto!=null) promocionesBD.dto = promotionJson.dto
            if(promotionJson.msi!=null) promocionesBD.msi = promotionJson.msi
            if(promotionJson.label!=null) promocionesBD.label = promotionJson.label
            if(promotionJson.urlImg!=null) promocionesBD.urlImg = promotionJson.urlImg
            if(promotionJson.isSpecial!=null) promocionesBD.isSpecial = promotionJson.isSpecial
            if(promotionJson.extraMsg!=null) promocionesBD.extraMsg = promotionJson.extraMsg
            if(promotionJson.urlImgEcommerce!=null) promocionesBD.urlImgEcommerce = promotionJson.urlImgEcommerce
            //println("holaaaa")
            println(promocionesBD)
            promosRepository.save(promocionesBD)
            return promocionesBD
        } else return throw promosExceptions(HttpStatus.NOT_MODIFIED,"No se pudieron actualizar los datos")

    }

    /*----UPDATE FECHA RECOMENDADA-----*/
 fun actualizarRecomendada(){
     var Recomendada = aseguradoraRecomendadaRepository.findById()
 }
}