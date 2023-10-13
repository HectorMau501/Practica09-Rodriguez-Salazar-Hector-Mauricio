package com.example.practica09_rodriguez_salazar_hector_mauricio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Contacto.newInstance] factory method to
 * create an instance of this fragment.
 */
class Contacto : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var email: EditText
    private lateinit var antiques: Spinner
    private var antiguedadesSel: String = "Joyas"


    private var antigüedad: Antigüedad = Antigüedad()

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
        val view = inflater.inflate(R.layout.fragment_contacto, container, false)

        name = view.findViewById(R.id.editNombre)
        phone = view.findViewById(R.id.editTelefono)
        email = view.findViewById(R.id.editCorreo)
        antiques = view.findViewById(R.id.spnArticulos)

        val lstAntiguedades = resources.getStringArray(R.array.antiguedades)
        val adaptadorSpn = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item,lstAntiguedades)
        antiques.adapter = adaptadorSpn
        antiques.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                antiguedadesSel = lstAntiguedades[p2]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        val btnLimpiar = view.findViewById<FloatingActionButton>(R.id.btnLimpiar)
        btnLimpiar.setOnClickListener { v ->
            limpiar()
        }

        val btnRegistrar = view.findViewById<FloatingActionButton>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener{ v ->
            registrar()
        }


        // Inflate the layout for this fragment
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Contacto.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Contacto().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun registrar() {
        if(name.text.isNotEmpty() && name.text.isNotBlank() &&
            phone.text.isNotEmpty() && email.text.isNotBlank() &&
            email.text.isNotEmpty() && email.text.isNotBlank()){
            val objetoAntigüedad = Antigüedad()

            objetoAntigüedad.nombre = name.text.toString()
            objetoAntigüedad.telefono = phone.text.toString().toInt()
            objetoAntigüedad.correo = email.text.toString()
            objetoAntigüedad.antiguedad = antiguedadesSel
            Toast.makeText(requireContext(),"Nombre: ${objetoAntigüedad.nombre}", Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(),"Telefono: ${objetoAntigüedad.telefono}", Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(),"Correo: ${objetoAntigüedad.correo}", Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(),"Antiguedad: ${objetoAntigüedad.antiguedad}", Toast.LENGTH_SHORT).show()
        }
    }

    fun limpiar() {
        name.text.clear()
        phone.text.clear()
        email.text.clear()
    }
}