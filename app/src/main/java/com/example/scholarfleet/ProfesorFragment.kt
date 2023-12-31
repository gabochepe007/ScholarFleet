package com.example.scholarfleet

import ProfesorAdapter
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.databinding.FragmentProfesorBinding

//SQLite
//import com.example.scholarfleet.database.Database
//import com.example.scholarfleet.models.Professor

//Firebase
import com.example.scholarfleet.firebase.Professor
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfesorFragment : Fragment() {
    private lateinit var binding: FragmentProfesorBinding
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfesorBinding.inflate(inflater, container, false)

        val recyclerView: RecyclerView = binding.listProfessor
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Aplica el ItemDecoration para agregar espacio entre los elementos
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_between_items)
        val itemSpacingDecoration = ItemSpacingDecoration(spacingInPixels)
        recyclerView.addItemDecoration(itemSpacingDecoration)

        //SQLite
       /* val database = Database(requireContext())
        val listaProfesores = database.getAllProfessors()

        val profesores = Professor()
        val allProfessors = profesores.getAllProfessors(listaProfesores)

        val adapter = ProfesorAdapter()
        recyclerView.adapter = adapter
        adapter.setProfesores(allProfessors)

        return binding.root*/

        //Firebase
        val databaseRef = FirebaseDatabase.getInstance().getReference("Profesores")
        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val profesoresList = mutableListOf<Professor>()
                for (dataSnapshot in snapshot.children) {
                    val professor = Professor.fromSnapshot(dataSnapshot)
                    profesoresList.add(professor)
                }
                val adapter = ProfesorAdapter()
                recyclerView.adapter = adapter
                adapter.setProfesores(profesoresList)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Database", "Error al obtener los profesores: ${error.message}")
            }
        })

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfesorFragment().apply {
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
}
