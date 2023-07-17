package com.example.scholarfleet

import ProfesorAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.database.Database
import com.example.scholarfleet.databinding.FragmentProfesorBinding
import com.example.scholarfleet.models.Professor


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfesorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfesorFragment : Fragment() {
    private lateinit var binding: FragmentProfesorBinding
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        binding = FragmentProfesorBinding.inflate(inflater, container, false)

        //recyclerview
        val recyclerView: RecyclerView = binding.listProfessor
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Consulta
        val database = Database(requireContext())
        val listaProfesores = database.getAllProfessors()

        //Procesado de consulta
        val profesores = Professor()
        val allProfessors = profesores.getAllProfessors(listaProfesores);

        //adapter
        val adapter = ProfesorAdapter()
        recyclerView.adapter = adapter
        adapter.setProfesores(allProfessors)

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfesorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfesorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}