package com.example.scholarfleet.database

import android.content.Context
import android.util.Log
import com.example.scholarfleet.firebase.Agenda
import com.example.scholarfleet.firebase.Materia
import com.example.scholarfleet.firebase.Professor
import com.google.firebase.database.*

class Firebase(context: Context) {

    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().reference

    //Profesores

    fun insertProfessor(professor: Professor){
        val professorRef = databaseRef.child("Profesores").push()
        professorRef.setValue(professor)
            .addOnSuccessListener {
                Log.d("Database", "Profesor insertado correctamente en Firebase")
            }
            .addOnFailureListener {
                Log.e("Database", "Error al insertar el profesor en Firebase: ${it.message}")
            }
    }

    // Función para obtener todos los profesores desde Firebase
    fun getAllProfessors(callback: (List<Professor>) -> Unit) {
        val professorRef = databaseRef.child("Profesores")

        professorRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val professorsList = mutableListOf<Professor>()
                for (dataSnapshot in snapshot.children) {
                    val professor = dataSnapshot.getValue(Professor::class.java)
                    professor?.let { professorsList.add(it) }
                }
                callback(professorsList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Database", "Error al obtener los profesores: ${error.message}")
                callback(emptyList())
            }
        })
    }

    //Materias

    fun insertMateria(materia: Materia) {
        val materiaRef = databaseRef.child("Materias").push()
        materiaRef.setValue(materia)
            .addOnSuccessListener {
                Log.d("Database", "Materia insertada correctamente en Firebase")
            }
            .addOnFailureListener {
                Log.e("Database", "Error al insertar la materia en Firebase: ${it.message}")
            }
    }

    // Función para obtener todas las materias desde Firebase
    fun getAllMaterias(callback: (List<Materia>) -> Unit) {
        val materiaRef = databaseRef.child("Materias")

        materiaRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val materiasList = mutableListOf<Materia>()
                for (dataSnapshot in snapshot.children) {
                    val materia = dataSnapshot.getValue(Materia::class.java)
                    materia?.let { materiasList.add(it) }
                }
                callback(materiasList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Database", "Error al obtener las materias: ${error.message}")
                callback(emptyList())
            }
        })
    }

    //Eventos
    // Función para insertar un evento en Firebase
    fun insertEvento(agenda: Agenda) {
        val eventoRef = databaseRef.child("Agenda").push()
        eventoRef.setValue(agenda)
            .addOnSuccessListener {
                Log.d("Database", "Evento insertado correctamente en Firebase")
            }
            .addOnFailureListener {
                Log.e("Database", "Error al insertar el evento en Firebase: ${it.message}")
            }
    }

    // Función para obtener todos los eventos desde Firebase
    fun getAllEvents(callback: (List<Agenda>) -> Unit) {
        val eventoRef = databaseRef.child("Agenda")

        eventoRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val eventsList = mutableListOf<Agenda>()
                for (dataSnapshot in snapshot.children) {
                    val evento = dataSnapshot.getValue(Agenda::class.java)
                    evento?.let { eventsList.add(it) }
                }
                callback(eventsList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Database", "Error al obtener los eventos: ${error.message}")
                callback(emptyList())
            }
        })
    }

}