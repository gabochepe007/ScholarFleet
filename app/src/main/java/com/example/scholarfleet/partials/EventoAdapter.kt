package com.example.scholarfleet.partials

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.R
import com.example.scholarfleet.models.Agenda
import com.example.scholarfleet.models.Materia

class EventoAdapter: RecyclerView.Adapter<EventoAdapter.EventoViewHolder> () {
    private val eventoList = ArrayList<Agenda>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventoAdapter.EventoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_evento, parent, false)
        return EventoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventoAdapter.EventoViewHolder, position: Int) {
        val evento = eventoList[position]
        holder.bind(evento)
    }

    override fun getItemCount(): Int {
        return eventoList.size
    }

    fun setEventos(eventos: List<Agenda>){
        eventoList.clear()
        eventoList.addAll(eventos)
        notifyDataSetChanged()
    }


    class EventoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val logoTextView: TextView = itemView.findViewById(R.id.logoL)

        fun bind(evento: Agenda){
            nombreTextView.text = evento.nombre
            val firstLetter = evento.nombre.firstOrNull()?.toString()?.toUpperCase() ?: ""
            logoTextView.text = firstLetter


            val colors = arrayOf(
                R.color.color1, R.color.color2, R.color.color3,
                R.color.color4,
                R.color.color5,
                R.color.color6)
            val randomColorRes = colors.random()
            val randomColor = ContextCompat.getColor(itemView.context, randomColorRes)


            logoTextView.backgroundTintList = ColorStateList.valueOf(randomColor)
        }
    }
}