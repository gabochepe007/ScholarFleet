package com.example.scholarfleet.ui.home

import android.annotation.SuppressLint
import com.example.scholarfleet.partials.HomeAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.R
import com.example.scholarfleet.database.Database

class HomeFragment : Fragment() {

    // Declara las referencias a los elementos del layout
    private lateinit var textFecha: TextView
    private lateinit var recyclerView: RecyclerView

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragmento
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Obtiene las referencias a los elementos del layout
       // textFecha = rootView.findViewById(R.id.idfecha)
        recyclerView = rootView.findViewById(R.id.recyclerView)

        // Configura el RecyclerView y su adaptador
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = HomeAdapter(getSampleData())

        // Obtiene la fecha actual y la establece en el TextView
      //  val currentDate = Calendar.getInstance().time
      //  val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
     //   val formattedDate = dateFormat.format(currentDate)
    //    textFecha.text = formattedDate

        return rootView
    }

    // Función de ejemplo para obtener datos para el RecyclerView
    private fun getSampleData(): List<String> {
        return listOf(
            "Evento 1",
            "Evento 2",
            "Evento 3",
            "Evento 4",
            "Evento 5",
            "Evento 6",
            "Evento 7"
            // Agrega más elementos aquí según tus necesidades
        )
    }
}
