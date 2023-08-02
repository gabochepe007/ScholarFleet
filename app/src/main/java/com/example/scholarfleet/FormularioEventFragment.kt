package com.example.scholarfleet

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.scholarfleet.database.Database
import com.example.scholarfleet.databinding.FragmentFormularioEventBinding
import com.example.scholarfleet.models.Agenda
import com.example.scholarfleet.utils.convertirFecha
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FormularioEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FormularioEventFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFormularioEventBinding

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
        binding = FragmentFormularioEventBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_formulario_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fecha.setOnClickListener {
            showDatePicker()
        }
        binding.btnguardar.setOnClickListener{
            if(validarCampos()){
                guardarEvento()
            }
        }
    }

    private fun validarCampos(): Boolean {
        val nombre = binding.nombre.text.toString()
        val fecha = binding.fecha.text.toString()
        val nota = binding.nota.text.toString()
        val materia = binding.materia.text.toString()
        val categoria = binding.categoria.text.toString()

        if (nombre.isEmpty() || fecha.isEmpty() || nota.isEmpty() || materia.isEmpty() || categoria.isEmpty()){
            Toast.makeText(requireContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun guardarEvento(){
        val evento = Agenda()
        evento.nombre = binding.nombre.text.toString()

        evento.fecha = convertirFecha(binding.fecha.text.toString())
        evento.nota = binding.nota.text.toString()
        evento.categoria = binding.categoria.text.toString()

        val idMatString = binding.materia.text.toString()
        val idMatEntero: Int = idMatString.toInt()
        evento.id_mat = idMatEntero

        val database = Database(requireContext())
        database.insertEvento(evento)

        Toast.makeText(requireContext(), "Datos de la materia guardados", Toast.LENGTH_SHORT).show()

        dismiss()
    }

    private fun showDatePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth)

            val timeZone = TimeZone.getTimeZone("America/Mexico_City")

            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            dateFormat.timeZone = timeZone

            val formattedDate = dateFormat.format(selectedDate.time)

            binding.fecha.setText(formattedDate)
        }, year, month, dayOfMonth)

        datePickerDialog.show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FormularioEventFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FormularioEventFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}