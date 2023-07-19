package com.example.scholarfleet.models

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.scholarfleet.database.Database
import java.util.concurrent.atomic.AtomicInteger

class Professor {
    var id: Int = getAutoId()
    var nombre: String = ""
    var telefono: String = ""
    var correo: String = ""
    var direccion: String = ""
    var horario: String = ""

    companion object {
        private val idCounter = AtomicInteger(0)

        fun getAutoId(): Int {
            return idCounter.getAndIncrement()
        }
    }

    @SuppressLint("Range")
    fun getAllProfessors(cursor: Cursor?): List<Professor> {
        val profesoresList = mutableListOf<Professor>()

        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndex("id_prof"))
                val nombre = getString(getColumnIndex("nomb_pro"))

                val profesor = Professor()
                profesor.id = id
                profesor.nombre = nombre

                profesoresList.add(profesor)
            }
        }

        cursor?.close()

        return profesoresList
    }
}
