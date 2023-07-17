package com.example.scholarfleet.models

import java.util.concurrent.atomic.AtomicInteger

class Professor {
    var id: Int = getAutoId()
    var nombre: String =""
    var telefono: String =""
    var correo: String =""
    var direccion: String =""
    var horario: String =""

    companion object {
        private val idCounter = AtomicInteger(0)

        fun getAutoId(): Int {
            return idCounter.getAndIncrement()
        }
    }

}