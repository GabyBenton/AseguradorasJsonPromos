package com.corepersistence.service.promociones.controller

import com.corepersistence.service.promociones.model.InsurersJson
import com.corepersistence.service.promociones.model.Json
import com.corepersistence.service.promociones.model.PromotionJson
import com.corepersistence.service.promociones.service.AseguradorasPromosServicio
import com.corepersistence.service.promociones.service.ResponseAseguradoraService
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import org.apache.commons.io.IOUtils
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.io.File
import java.nio.charset.StandardCharsets


@RestController
class AseguradoraPromosController() {
    @Autowired
    lateinit var responseAseguradoraService: ResponseAseguradoraService
    @Autowired
    lateinit var aseguradorasPromosServicio: AseguradorasPromosServicio
    //private val objectMapper= ObjectMapper()
    //@Value("classpath:data.json")
    //var datitos: Resource? = null
    //val staticDataResource = ClassPathResource("data.json")
    //val staticDataString: String = IOUtils.toString(staticDataResource.inputStream, StandardCharsets.UTF_8)

    @GetMapping("/imprimir")

        fun imprimirDatitos(): Json? = aseguradorasPromosServicio.imprimirDatitos()

    @GetMapping("/agregar")
    fun agregar() = aseguradorasPromosServicio.agregar()


    @GetMapping("/mostrar")
    fun mostrar(): MutableList<Any> {
       var data = responseAseguradoraService.getDatos()

        return data
    }

    @GetMapping("/datitos")
    fun getDatitos(): Json? =responseAseguradoraService.getDatitos()

    @GetMapping("/{name}")
    fun mostrarName(@PathVariable("name")name:String?)=responseAseguradoraService.getPorName(name)


    @PostMapping("/enviar/{name}")
    fun agregarPromos(@PathVariable("name")name:String?,@RequestBody promotionJson: PromotionJson)=responseAseguradoraService.agregarPromos(name,promotionJson)

    @DeleteMapping("/eliminar/{name}/{tipo}")
    fun borrarPromos(@PathVariable("name")name:String?, @PathVariable("tipo")tipo:String?)=
        responseAseguradoraService.borrarPromos(name,tipo)

    @PutMapping("/actualizar/{name}/{tipo}")
    fun actualizarPromos(@PathVariable("name")name:String?, @PathVariable("tipo")tipo:String?,@RequestBody promotionJson: PromotionJson)=
        responseAseguradoraService.actualizarPromos(name,tipo,promotionJson)

    /*fun imprimirDatitos(): Json? {
        //var data = JSONObject(staticDataString).get("insurers").toString()
        //var dataArray = JSONArray(data)
        //val jsonFilePath = "src/main/resources/data.json"
        val jsonData = objectMapper.readValue(staticDataString, Json::class.java)
        println(jsonData)

        var info = jsonData.insurers?.get(0)?.name
        println(info)
        return jsonData
    }*/


    //@GetMapping("/datitosDB")



    //@GetMapping("/datitos/{name}")



}






        //return jsonData
        //val convertedObject = Gson().fromJson(dataArray, Json::class.java)


        //println(convertedObject)
        //return convertedObject

        //for (i in 0 until dataArray.length()) {

            // store each object in JSONObject
        //    var explrObject = dataArray.get(i)

            // get field value from JSONObject using get() method
        //    println(explrObject.toString())
        //}


        //println(data)

//        for (i in 0 until key.length()) {
//            val keys = key.getString(i)
//            val value: Any? = data.get(keys.toString())

//            println(value)
//        }


        /**
         *  1.- Iterar el json para obtener la info por array
         *  2.- Llenar el modelo de aseguradoras (array)
         *  3.- Llenar el modelo tipo (array)
         *  4.- Llenar el modelo Promociones (array)
         *  5.- Imprimir el resultado
         *  6.- Exponer que hicieron y porque
         *
         *
         * **/

        //println(staticDataResource)



