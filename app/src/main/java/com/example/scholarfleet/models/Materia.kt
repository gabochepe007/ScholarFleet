package com.example.scholarfleet.models

import android.annotation.SuppressLint
import android.database.Cursor
import java.util.concurrent.atomic.AtomicInteger

class Materia {
    var id: Int = getAutoId()
    var nombre : String = ""
    var edificio : String = ""
    var salon : String = ""
    var nota : String = ""
    var horario : String = ""

    companion object{
        private val idCounter = AtomicInteger(1)

        fun getAutoId(): Int {
            return idCounter.getAndIncrement()
        }
    }

    @SuppressLint("Range")
    fun getAllMaterias(cursor: Cursor?): List<Materia> {
        val materiaList = mutableListOf<Materia>()

        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndex("id_mat"))
                val nombre = getString(getColumnIndex("nombre"))

                val materia = Materia()
                materia.id = id
                materia.nombre = nombre

                materiaList.add(materia)
            }
        }

        cursor?.close()

        return materiaList
    }
}