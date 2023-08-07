package com.example.scholarfleet.firebase

import com.google.firebase.database.DataSnapshot

data class Agenda(
    var id_agenda: Int = 0,
    var nombre: String = "",
    var fecha: String = "",
    var imagen: ByteArray? = null,
    var nota: String? = null,
    var id_mat: Int = 0,
    var categoria: String = ""
){
    companion object {
        fun fromSnapshot(snapshot: DataSnapshot): Agenda {
            val agenda = snapshot.getValue(Agenda::class.java)
            agenda?.id_agenda = snapshot.child("id_agenda").getValue(Int::class.java) ?: 0
            return agenda ?: Agenda()
        }
    }
}
