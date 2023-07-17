package com.example.scholarfleet.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.scholarfleet.models.Professor

class Database(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "agenda.db"
        private const val DATABASE_VERSION = 1
        //Tabla profesores
        private const val TABLE_NAME_PROFESORES = "Profesores"
        private const val COLUMN_ID_PROFESOR = "id_prof"
        private const val COLUMN_NOMBRE_PROFESOR = "nomb_pro"
        private const val COLUMN_TELEFONO = "Telefono"
        private const val COLUMN_CORREO = "correo"
        private const val COLUMN_DIRECCION = "direccion"
        private const val COLUMN_HORARIO = "horario"
        //private const val COLUMN_FOTO = "foto"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQueries = arrayOf(
            "CREATE TABLE Profesores (id_prof INTEGER NOT NULL, nomb_pro TEXT NOT NULL, Telefono TEXT, correo TEXT, direccion TEXT, horario TEXT NOT NULL, foto BLOB, PRIMARY KEY(id_prof))",
            "CREATE TABLE profesor_has_materias (id_prof INTEGER NOT NULL, id_mat INTEGER NOT NULL, fk_id_prof INTEGER NOT NULL, fk_id_mat INTEGER NOT NULL, FOREIGN KEY (fk_id_prof) REFERENCES Profesores(id_prof), FOREIGN KEY (fk_id_mat) REFERENCES materias(id_mat))",
            "CREATE TABLE materias (id_mat INTEGER NOT NULL, nombre TEXT NOT NULL, aula TEXT NOT NULL, horario TEXT NOT NULL, nota TEXT, PRIMARY KEY(id_mat))",
            "CREATE TABLE Agenda (id_agenda INTEGER NOT NULL, nombre TEXT NOT NULL, fecha TEXT NOT NULL, imagen BLOB, nota TEXT, id_mat INTEGER NOT NULL, categoria TEXT NOT NULL, fk_id_mat INTEGER NOT NULL, FOREIGN KEY (fk_id_mat) REFERENCES materias(id_mat), PRIMARY KEY(id_agenda))"
        )

        for (createQuery in createTableQueries) {
            db.execSQL(createQuery)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val dropTableQueries = arrayOf(
            "DROP TABLE IF EXISTS Profesores",
            "DROP TABLE IF EXISTS profesor_has_materias",
            "DROP TABLE IF EXISTS materias",
            "DROP TABLE IF EXISTS Agenda"
        )

        for (dropQuery in dropTableQueries) {
            db.execSQL(dropQuery)
        }

        onCreate(db)
    }

    fun insertProfessor(professor: Professor) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ID_PROFESOR, professor.id)
            put(COLUMN_NOMBRE_PROFESOR, professor.nombre)
            put(COLUMN_TELEFONO, professor.telefono)
            put(COLUMN_CORREO, professor.correo)
            put(COLUMN_DIRECCION, professor.direccion)
            put(COLUMN_HORARIO, professor.horario)
            //put(COLUMN_FOTO, professor.foto)
        }
        db.insert(TABLE_NAME_PROFESORES, null, values)
        db.close()
    }
}