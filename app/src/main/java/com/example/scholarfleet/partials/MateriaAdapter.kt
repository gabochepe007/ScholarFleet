package com.example.scholarfleet.partials

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.R
import com.example.scholarfleet.models.Materia

class MateriaAdapter: RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder>() {
    private val materiaList = ArrayList<Materia>()

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int): MateriaAdapter.MateriaViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_materia, parent, false)
        return MateriaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MateriaAdapter.MateriaViewHolder, position: Int) {
       val materia = materiaList[position]
       holder.bind(materia)
    }

    override fun getItemCount(): Int {
        return materiaList.size
    }

    fun setMaterias(materias: List<Materia>){
        materiaList.clear()
        materiaList.addAll(materias)
        notifyDataSetChanged()
    }

    class MateriaViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)
        private val logoTextView: TextView = itemView.findViewById(R.id.logoL)

        fun bind(materia: Materia){
            nombreTextView.text = materia.nombre
            val firstLetter = materia.nombre.firstOrNull()?.toString()?.toUpperCase() ?: ""
            logoTextView.text = firstLetter


            val colors = arrayOf(R.color.color1, R.color.color2, R.color.color3,R.color.color4,R.color.color5,R.color.color6)
            val randomColorRes = colors.random()
            val randomColor = ContextCompat.getColor(itemView.context, randomColorRes)


            logoTextView.backgroundTintList = ColorStateList.valueOf(randomColor)
        }
    }

}