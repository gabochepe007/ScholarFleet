package com.example.scholarfleet.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.R
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    // Declara las referencias a los elementos del layout
    private lateinit var textDiaM: TextView
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragmento
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Obtiene las referencias a los elementos del layout
       // textDiaM = rootView.findViewById(R.id.textDiaM)
        recyclerView = rootView.findViewById(R.id.recyclerView)

        // Configura el RecyclerView y su adaptador
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = HomeAdapter(getSampleData())

        // Obtiene la fecha actual y la establece en el TextView
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormat.format(currentDate)
      //  textDiaM.text = formattedDate

        return rootView
    }

    // Función de ejemplo para obtener datos para el RecyclerView
    private fun getSampleData(): List<String> {
        return listOf(
            "Materia 1",
            "Materia 2",
            "Materia 3",
            "Materia 1",
            "Materia 2",
            "Materia 3",
            "Materia 1",

            // Agrega más elementos aquí según tus necesidades
        )
    }
}
