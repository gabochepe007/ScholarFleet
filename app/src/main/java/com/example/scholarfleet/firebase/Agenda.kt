package com.example.scholarfleet.firebase

data class Agenda(
    val id_agenda: Int = 0,
    val nombre: String = "",
    val fecha: String = "",
    val imagen: ByteArray? = null,
    val nota: String? = null,
    val id_mat: Int = 0,
    val categoria: String = ""
)
