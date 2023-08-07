package com.example.scholarfleet.ui.materia

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.ProfesorFragment
import com.example.scholarfleet.R
import com.example.scholarfleet.databinding.FragmentMateriaBinding
import com.example.scholarfleet.partials.MateriaAdapter


//Sqlite
//import com.example.scholarfleet.database.Database
//import com.example.scholarfleet.models.Materia

//Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.scholarfleet.firebase.Materia



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MateriaFragment : Fragment() {
    private var _binding: FragmentMateriaBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val galleryViewModel = ViewModelProvider(this).get(MateriaViewModel::class.java)

        _binding = FragmentMateriaBinding.inflate(inflater, container, false)


        val recyclerView: RecyclerView = binding.listMateria
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_between_items)
        val itemSpacingDecoration = ProfesorFragment.ItemSpacingDecoration(spacingInPixels)
        recyclerView.addItemDecoration(itemSpacingDecoration)

        //SQLite
       /* val database = Database(requireContext())
        val listaMaterias = database.getAllMaterias()

        val materias = Materia()
        val allMaterias = materias.getAllMaterias(listaMaterias)

        val adapter = MateriaAdapter()
        recyclerView.adapter = adapter
        adapter.setMaterias(allMaterias)
        return binding.root*/

        //Firebase
        val databaseRef = FirebaseDatabase.getInstance().getReference("Materia")
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val materiaList = mutableListOf<Materia>()
                for (dataSnapshot in snapshot.children) {
                    val materia = Materia.fromSnapshot(dataSnapshot)
                    materiaList.add(materia)
                }
                val adapter = MateriaAdapter()
                recyclerView.adapter = adapter
                adapter.setMaterias(materiaList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Database", "Error al obtener los registros de Materia: ${error.message}")
            }
        })

        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MateriaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    class ItemSpacingDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = spacing
            outRect.right = spacing
            outRect.bottom = spacing

            if (parent.getChildAdapterPosition(view) != 0) {
                outRect.top = spacing
            } else {
                outRect.top = 0
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}