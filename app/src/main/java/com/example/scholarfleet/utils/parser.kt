package com.example.scholarfleet.utils

fun convertirFecha(fechaOriginal: String): String {
    val partes = fechaOriginal.split("/")
    val dia = partes[0]
    val mes = partes[1]
    val anio = partes[2]

    return "$anio-$mes-$dia"
}
