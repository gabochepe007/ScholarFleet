package com.example.scholarfleet.firebase

import com.google.firebase.database.DataSnapshot

data class Materia(
    var id_mat: Int = 0,
    var nombre: String = "",
    var aula: String? = null,
    var horario: String = "",
    var nota: String? = null
){
    companion object {
        fun fromSnapshot(snapshot: DataSnapshot): Materia {
            val materia = snapshot.getValue(Materia::class.java)
            materia?.id_mat = snapshot.child("id_mat").getValue(Int::class.java) ?: 0
            return materia ?: Materia()
        }
    }
}
