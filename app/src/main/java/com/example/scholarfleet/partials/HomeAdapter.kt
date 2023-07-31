package com.example.scholarfleet.partials

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.R
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter(private val data: List<String>) :

    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val fechaTextView: TextView = itemView.findViewById(R.id.idfecha)
        private val infoTextView: TextView = itemView.findViewById(R.id.infoTextView)
        private val boton: Button = itemView.findViewById(R.id.boton)

        fun bind(nombreMateria: String, position: Int) {
            nombreTextView.text = nombreMateria

            // Obtiene la fecha actual
            val currentDate = Calendar.getInstance().time
            val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(currentDate)

            // Agrega la posición del elemento (día a incrementar) para obtener la fecha del día siguiente
            val calendar = Calendar.getInstance()
            calendar.time = currentDate
            calendar.add(Calendar.DAY_OF_YEAR, position)

            val tomorrowDate = calendar.time
            val formattedTomorrowDate = dateFormat.format(tomorrowDate)

            // Asigna la fecha según la posición del elemento
            fechaTextView.text = if (position == 0) {
                formattedDate // Fecha actual para el primer elemento
            } else {
                formattedTomorrowDate // Fecha del día siguiente para los siguientes elementos
            }

            // Asigna el texto del día correspondiente al infoTextView
            val dayText = when (position) {
                0 -> "Hoy"
                1 -> "Mañana"
                else -> getDayText(formattedTomorrowDate)
            }
            infoTextView.text = dayText

            //
            boton.setOnClickListener {



            }
        }

        private fun getDayText(dateString: String): String {
            val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).parse(dateString)
            val calendar = Calendar.getInstance()
            calendar.time = date
            val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
            return when (dayOfWeek) {
                Calendar.SUNDAY -> "Domingo"
                Calendar.MONDAY -> "Lunes"
                Calendar.TUESDAY -> "Martes"
                Calendar.WEDNESDAY -> "Miércoles"
                Calendar.THURSDAY -> "Jueves"
                Calendar.FRIDAY -> "Viernes"
                Calendar.SATURDAY -> "Sábado"
                else -> ""
            }
        }
    }
}
