package com.example.scholarfleet.models

import android.annotation.SuppressLint
import android.database.Cursor
import java.util.concurrent.atomic.AtomicInteger

class Agenda {
    var id: Int = getAutoId()
    var nombre: String = ""
    var fecha: String = ""
    var nota: String = ""
    var id_mat: Int = 0 //ForeignKey
    var categoria: String = ""

    companion object{
        private val idCounter = AtomicInteger(1)

        fun getAutoId():Int{
            return idCounter.getAndIncrement()
        }
    }

    @SuppressLint("Range")
    fun getAllEvents(cursor: Cursor?): List<Agenda> {
        val agendaList = mutableListOf<Agenda>()

        cursor?.apply {
            while (moveToNext()) {
                val id = getInt(getColumnIndex("id_agenda"))
                val nombre = getString(getColumnIndex("nombre"))

                val agenda = Agenda()
                agenda.id = id
                agenda.nombre = nombre

                agendaList.add(agenda)
            }
        }

        cursor?.close()

        return agendaList
    }
}