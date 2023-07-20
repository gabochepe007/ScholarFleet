import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.text.InputType
import android.view.inputmethod.EditorInfo
import com.example.scholarfleet.R
import com.example.scholarfleet.database.Database
import com.example.scholarfleet.databinding.FormularioproFragmentBinding
import com.example.scholarfleet.models.Professor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FormularioProFragment : BottomSheetDialogFragment() {
    private lateinit var btnSaveProfesor: Button
    private lateinit var binding: FormularioproFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FormularioproFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el campo de teléfono para abrir el teclado numérico
        binding.etTelefonoProfesor.inputType = InputType.TYPE_CLASS_NUMBER
        binding.etTelefonoProfesor.keyListener = null
        binding.etTelefonoProfesor.setRawInputType(InputType.TYPE_CLASS_NUMBER)
        binding.etTelefonoProfesor.setTextIsSelectable(true)

        binding.etTelefonoProfesor.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.etTelefonoProfesor.inputType = InputType.TYPE_CLASS_NUMBER
                binding.etTelefonoProfesor.setTextIsSelectable(true)
            }
        }

        binding.etTelefonoProfesor.setOnClickListener {
            binding.etTelefonoProfesor.inputType = InputType.TYPE_CLASS_NUMBER
            binding.etTelefonoProfesor.setTextIsSelectable(true)
        }

        binding.etTelefonoProfesor.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                binding.etTelefonoProfesor.clearFocus()
            }
            false
        }

        binding.btnGuardarProfesor.setOnClickListener {
            if (validarCampos()) {
                guardarProfesor()
            }
        }

        binding.btnHora.setOnClickListener {
            val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val currentMinute = Calendar.getInstance().get(Calendar.MINUTE)

            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(currentHour)
                .setMinute(currentMinute)
                .build()

            picker.addOnPositiveButtonClickListener {
                val hour = picker.hour
                val minute = picker.minute

                val selectedTime = formatTime(hour, minute)
                binding.etHorarioProfesor.setText(selectedTime)
            }

            picker.show(parentFragmentManager, "TimePicker")
        }

        binding.etHorarioProfesor.isEnabled = false
    }

    private fun formatTime(hour: Int, minute: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return format.format(calendar.time)
    }

    private fun validarCampos(): Boolean {
        val nombreProfesor = binding.etNombreProfesor.text.toString()
        val telefonoProfesor = binding.etTelefonoProfesor.text.toString()
        val correoProfesor = binding.etCorreoProfesor.text.toString()
        val direccionProfesor = binding.etDireccionProfesor.text.toString()
        val horarioProfesor = binding.etHorarioProfesor.text.toString()

        if (nombreProfesor.isBlank() || telefonoProfesor.isBlank() || correoProfesor.isBlank() || direccionProfesor.isBlank() || horarioProfesor.isBlank()) {
            Toast.makeText(requireContext(), "Todos los campos son requeridos", Toast.LENGTH_SHORT).show()
            return false
        }

        val telefonoPattern = "^[0-9]*$".toRegex()
        if (!telefonoPattern.matches(telefonoProfesor)) {
            binding.etTelefonoProfesor.error = "Solo se permiten números"
            return false
        } else if (telefonoProfesor.length != 10) {
            binding.etTelefonoProfesor.error = "Debe tener 10 dígitos"
            return false
        } else {
            binding.etTelefonoProfesor.error = null
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        if (!emailPattern.matches(correoProfesor)) {
            binding.etCorreoProfesor.error = "Correo electrónico inválido"
            return false
        } else {
            binding.etCorreoProfesor.error = null
        }



        return true
    }

    private fun guardarProfesor() {

        val professor = Professor()
        professor.nombre = binding.etNombreProfesor.text.toString()
        professor.telefono = binding.etTelefonoProfesor.text.toString()
        professor.correo = binding.etCorreoProfesor.text.toString()
        professor.direccion = binding.etDireccionProfesor.text.toString()
        professor.horario = binding.etHorarioProfesor.text.toString()

        val database = Database(requireContext())
        database.insertProfessor(professor)

        Toast.makeText(requireContext(), "Datos del profesor guardados", Toast.LENGTH_SHORT).show()

        dismiss()
    }
}
