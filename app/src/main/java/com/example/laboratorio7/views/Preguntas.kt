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
import kotlinx.android.synthetic.main.preguntas_fragment.*

/**
 * @author Bryann Alfaro
 * Fragment that displays the view for the question
 */
@Suppress("DEPRECATION")
class Preguntas : Fragment() {



    var texto:String=""
    var selecicon=""
    private lateinit var viewModel: EncuestaViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application= requireNotNull(this.activity!!).application
        val dataSource= SurveyDatabase.getDatabase(application).surveyDao()
        val encuestaFactory= EncuestaViewModelFactory(dataSource,application)
        viewModel=ViewModelProviders.of(this.activity!!,encuestaFactory).get(EncuestaViewModel::class.java)



        //inflate the view
        val bindingPreguntas=DataBindingUtil.inflate<PreguntasFragmentBinding>(inflater,R.layout.preguntas_fragment,container,false)
        var options= arrayOf("Texto","Numero","Rating")
        setHasOptionsMenu(true)

        bindingPreguntas.spinner.adapter=ArrayAdapter<String>(requireActivity(),R.layout.support_simple_spinner_dropdown_item,options)
        bindingPreguntas.spinner.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(activity,"hola",Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selecicon=spinner.getItemAtPosition(position).toString()
                Toast.makeText(activity,"$selecicon",Toast.LENGTH_SHORT).show()

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
        val spiner:Spinner=view!!.findViewById(R.id.spinner)

        texto=edit.text.toString()//pregunta

        spiner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                //  selecicon=spiner.getItemAtPosition(position).toString()
                //  Toast.makeText(activity,"$selecicon",Toast.LENGTH_SHORT).show()
                //To change body of created functions use File | Settings | File Templates.
            }

        }
        val application= requireNotNull(this.activity!!).application
        val dataSource= SurveyDatabase.getDatabase(application).surveyDao()
        val encuestaFactory= EncuestaViewModelFactory(dataSource,application)
        viewModel=ViewModelProviders.of(this.activity!!,encuestaFactory).get(EncuestaViewModel::class.java)
        viewModel.onAddQuestionRequested(texto,selecicon)


        Toast.makeText(activity,"Se agrego en DB",Toast.LENGTH_SHORT).show()
        edit.setText("")
        view!!.findNavController().navigate(R.id.action_preguntas_to_principal)
        return super.onOptionsItemSelected(item)
    }
}