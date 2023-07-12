import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.scholarfleet.R
import com.example.scholarfleet.databinding.ActivityHomeBinding
import com.example.scholarfleet.databinding.FormularioproFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FormularioProFragment : BottomSheetDialogFragment() {
    private lateinit var btnSaveProfesor: Button
    private lateinit var binding: FormularioproFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.formulariopro_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSaveProfesor = view.findViewById(R.id.btnGuardarProfesor)
        btnSaveProfesor.setOnClickListener {
            guardarProfesor()
        }
    }

    private fun guardarProfesor() {

        val idProfesor = binding.etIdProfesor.text.toString()
        val nombreProfesor = binding.etNombreProfesor.text.toString()
        val telefonoProfesor = binding.etTelefonoProfesor.text.toString()
        val correoProfesor = binding.etCorreoProfesor.text.toString()
        val direccionProfesor = binding.etDireccionProfesor.text.toString()
        val horarioProfesor = binding.etHorarioProfesor.text.toString()
        // Aquí puedes agregar la lógica para guardar los datos del profesor



        Toast.makeText(requireContext(), "Datos del profesor guardados", Toast.LENGTH_SHORT).show()
    }
}


