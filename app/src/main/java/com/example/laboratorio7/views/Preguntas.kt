package com.example.laboratorio7.views

import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.databinding.DataBindingUtil

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.laboratorio7.R
import com.example.laboratorio7.database.SurveyDatabase
import com.example.laboratorio7.databinding.PreguntasFragmentBinding
import com.example.laboratorio7.viewmodels.EncuestaViewModel
import com.example.laboratorio7.viewmodels.EncuestaViewModelFactory

/**
 * @author Bryann Alfaro
 * Fragment that displays the view for the question
 */
@Suppress("DEPRECATION")
class Preguntas : Fragment() {



    var texto:String=""
    private lateinit var viewModel: EncuestaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the view
        val bindingPreguntas=DataBindingUtil.inflate<PreguntasFragmentBinding>(inflater,R.layout.preguntas_fragment,container,false)
        var options= arrayOf("Texto","Numero","Raiting")//array for the spinner text
        setHasOptionsMenu(true)

        bindingPreguntas.spinner.adapter=ArrayAdapter(requireActivity(),R.layout.support_simple_spinner_dropdown_item,options)
        bindingPreguntas.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(activity,"Item",Toast.LENGTH_SHORT).show() //To change body of created functions use File | Settings | File Templates.
            }

        }
        return bindingPreguntas.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.save_menu,menu)
    }
    //when the button save is pressed
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val edit:EditText = view!!.findViewById(R.id.editText)
        val spinnerSelection:Spinner=view!!.findViewById(R.id.spinner)
        texto=edit.text.toString()
        var seleccion:String=""
        spinnerSelection.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               seleccion=spinnerSelection.getItemAtPosition(position).toString()

            }

        }
        //Instantiate the application and the datasource
        val application= requireNotNull(this.activity!!).application
        val dataSource= SurveyDatabase.getInstance(application).surveyDao
        //Make the factory
        val encuestaFactory= EncuestaViewModelFactory(dataSource,application)
        viewModel=ViewModelProviders.of(activity!!,encuestaFactory).get(EncuestaViewModel::class.java)
        viewModel.onAddQuestionRequested(texto,seleccion)
        Toast.makeText(activity,"Se agrego en DB",Toast.LENGTH_SHORT).show()
        edit.setText("")
        view!!.findNavController().navigate(R.id.action_preguntas_to_principal)
        return super.onOptionsItemSelected(item)
    }
}
