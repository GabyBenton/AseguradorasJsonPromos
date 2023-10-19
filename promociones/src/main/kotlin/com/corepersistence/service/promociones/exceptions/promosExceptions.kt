package com.corepersistence.service.promociones.exceptions

import org.springframework.http.HttpStatus

class promosExceptions (status: HttpStatus, mensaje: String):Exception(mensaje)