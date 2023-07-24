package com.example.scholarfleet

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import com.example.scholarfleet.database.Database
import com.example.scholarfleet.databinding.FormulariomatFragmentBinding
import com.example.scholarfleet.models.Materia
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class FormularioMatFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FormulariomatFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FormulariomatFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.salom.inputType = InputType.TYPE_CLASS_NUMBER
        binding.salom.keyListener = null
        binding.salom.setRawInputType(InputType.TYPE_CLASS_NUMBER)
        binding.salom.setTextIsSelectable(true)

        binding.salom.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.salom.inputType = InputType.TYPE_CLASS_NUMBER
                binding.salom.setTextIsSelectable(true)
            }
        }

        binding.salom.setOnClickListener {
            binding.salom.inputType = InputType.TYPE_CLASS_NUMBER
            binding.salom.setTextIsSelectable(true)
        }

        binding.salom.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.salom.clearFocus()
            }
            false
        }

        binding.btguardar.setOnClickListener {
            if (validarCampos()) {
                guardarMateria()
            }
        }

        binding.btnHora.setOnClickListener {
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        val timeZoneGMTMinus6 = TimeZone.getTimeZone("GMT-6")

        datePicker.addOnPositiveButtonClickListener { selection ->
            val calendar = Calendar.getInstance(timeZoneGMTMinus6)
            calendar.timeInMillis = selection

            val format = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
            format.timeZone = timeZoneGMTMinus6

            val selectedDate = format.format(calendar.time)
            binding.horario.setText(selectedDate)
        }

        datePicker.show(parentFragmentManager, "DatePicker")
    }





    private fun validarCampos(): Boolean {
        val nombre = binding.nombrem.text.toString()
        val aula = binding.salom.text.toString()
        val edificio = binding.edificiom.text.toString()
        val horario = binding.horario.text.toString()

        if (nombre.isEmpty() || aula.isEmpty() || edificio.isEmpty() || horario.isEmpty()) {
            Toast.makeText(requireContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private fun guardarMateria() {
        val materia = Materia()
        materia.nombre = binding.nombrem.text.toString()
        materia.salon = binding.salom.text.toString()
        materia.edificio = binding.edificiom.text.toString()
        materia.horario = binding.horario.text.toString()

        val database = Database(requireContext())
        database.insertMateria(materia)

        Toast.makeText(requireContext(), "Datos de la materia guardados", Toast.LENGTH_SHORT).show()

        dismiss()
    }
}
