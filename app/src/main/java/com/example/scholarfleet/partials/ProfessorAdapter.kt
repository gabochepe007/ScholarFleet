import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.scholarfleet.R
import com.example.scholarfleet.models.Professor

class ProfesorAdapter : RecyclerView.Adapter<ProfesorAdapter.ProfesorViewHolder>() {

    private val profesoresList = ArrayList<Professor>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesorViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_professor, parent, false)
        return ProfesorViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfesorViewHolder, position: Int) {
        val profesor = profesoresList[position]
        holder.bind(profesor)
    }

    override fun getItemCount(): Int {
        return profesoresList.size
    }

    fun setProfesores(profesores: List<Professor>) {
        profesoresList.clear()
        profesoresList.addAll(profesores)
        notifyDataSetChanged()
    }

    class ProfesorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nombreTextView: TextView = itemView.findViewById(R.id.nombreTextView)

        fun bind(profesor: Professor) {
            nombreTextView.text = profesor.nombre
        }
    }
}
