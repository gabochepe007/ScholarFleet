import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.scholarfleet.R
import com.example.scholarfleet.database.Database
import com.example.scholarfleet.databinding.FormularioproFragmentBinding
import com.example.scholarfleet.models.Professor
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

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
        //return inflater.inflate(R.layout.formulariopro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnGuardarProfesor.setOnClickListener {
            guardarProfesor()
        }

    }

    private fun guardarProfesor() {

        val nombreProfesor = binding.etNombreProfesor.text.toString()
        val telefonoProfesor = binding.etTelefonoProfesor.text.toString()
        val correoProfesor = binding.etCorreoProfesor.text.toString()
        val direccionProfesor = binding.etDireccionProfesor.text.toString()
        val horarioProfesor = binding.etHorarioProfesor.text.toString()
        // Aquí puedes agregar la lógica para guardar los datos del profesor

        val professor = Professor()
        professor.nombre = nombreProfesor
        professor.telefono = telefonoProfesor
        professor.correo = correoProfesor
        professor.direccion = direccionProfesor
        professor.horario = horarioProfesor

        val database = Database(requireContext())
        database.insertProfessor(professor)

        Toast.makeText(requireContext(), "Datos del profesor guardados", Toast.LENGTH_SHORT).show()
    }
}


