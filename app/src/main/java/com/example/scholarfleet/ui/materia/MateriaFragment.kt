package com.example.scholarfleet.ui.materia

import ProfesorAdapter
import android.graphics.Rect
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scholarfleet.ProfesorFragment
import com.example.scholarfleet.R
import com.example.scholarfleet.database.Database

import com.example.scholarfleet.databinding.FragmentMateriaBinding
import com.example.scholarfleet.models.Materia
import com.example.scholarfleet.partials.MateriaAdapter

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

        val database = Database(requireContext())
        val listaMaterias = database.getAllMaterias()

        val materias = Materia()
        val allMaterias = materias.getAllMaterias(listaMaterias)

        val adapter = MateriaAdapter()
        recyclerView.adapter = adapter
        adapter.setMaterias(allMaterias)
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