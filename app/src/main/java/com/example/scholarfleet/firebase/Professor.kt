package com.example.scholarfleet.firebase

import com.google.firebase.database.DataSnapshot

data class Professor(
    var id_prof: Int = 0,
    var nomb_pro: String = "",
    var telefono: String? = null,
    var correo: String? = null,
    var direccion: String? = null,
    var horario: String = ""
){

    companion object {
        fun fromSnapshot(snapshot: DataSnapshot): Professor {
            val professor = snapshot.getValue(Professor::class.java)
            professor?.id_prof = snapshot.child("id_prof").getValue(Int::class.java) ?: 0
            return professor ?: Professor()
        }
    }
}
