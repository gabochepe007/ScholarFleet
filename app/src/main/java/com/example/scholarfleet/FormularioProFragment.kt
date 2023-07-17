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

        val professor = Professor()
        professor.nombre    = binding.etNombreProfesor.text.toString()
        professor.telefono  = binding.etTelefonoProfesor.text.toString()
        professor.correo    = binding.etCorreoProfesor.text.toString()
        professor.direccion = binding.etDireccionProfesor.text.toString()
        professor.horario   = binding.etHorarioProfesor.text.toString()

       // Aquí puedes agregar la lógica para guardar los datos del profesor
        val database = Database(requireContext())
        database.insertProfessor(professor)

        Toast.makeText(requireContext(), "Datos del profesor guardados", Toast.LENGTH_SHORT).show()
    }
}


