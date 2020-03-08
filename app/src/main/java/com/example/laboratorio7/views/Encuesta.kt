package com.example.laboratorio7.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.laboratorio7.R
import com.example.laboratorio7.database.PreguntaEntity
import com.example.laboratorio7.database.SurveyDatabase
import com.example.laboratorio7.databinding.EncuestaFragmentBinding
import com.example.laboratorio7.viewmodels.EncuestaViewModel
import com.example.laboratorio7.viewmodels.EncuestaViewModelFactory
import com.example.laboratorio7.viewmodels.ResultadoViewModel
import com.example.laboratorio7.viewmodels.ResultadoViewModelFactory

/**
 * @author Bryann Alfaro
 * Fragment that displays the questions
 */

@Suppress("DEPRECATION")
class Encuesta : Fragment() {
    private lateinit var viewModel: EncuestaViewModel
    private lateinit var viewModelR:ResultadoViewModel
    private lateinit var bindingEncuesta: EncuestaFragmentBinding

    var contador=0

    private lateinit var lista:List<PreguntaEntity>




    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application= requireNotNull(this.activity).application
        val dataSource=SurveyDatabase.getDatabase(application).surveyDao()
        val encuestaFactory=EncuestaViewModelFactory(dataSource,application)
        val respuestaFactory=ResultadoViewModelFactory(dataSource,application)

        //inflate the view
        bindingEncuesta = DataBindingUtil.inflate(inflater, R.layout.encuesta_fragment, container, false)

        //Init the viewModels
        viewModelR=ViewModelProviders.of(activity!!,respuestaFactory).get(ResultadoViewModel::class.java)
        viewModel = ViewModelProviders.of(activity!!,encuestaFactory).get(EncuestaViewModel::class.java)

        bindingEncuesta.encuestaModel=viewModel
        viewModelR.endSurvey()



        bindingEncuesta.lifecycleOwner=viewLifecycleOwner
        //Observe for put the questions in the view

        viewModel.todas.observe(this, Observer {
            lista=it
            viewModel.createEncuesta()
            bindingEncuesta.preguntas.text= lista[contador].name
        })

        //next question
        bindingEncuesta.button2.setOnClickListener {

            if (contador==0){
                viewModelR.establecerRespuesta(bindingEncuesta.editText2.text.toString())
            }else if (contador==1){
                var rateValue=bindingEncuesta.ratingBar.rating
                viewModelR.rating(rateValue)
                viewModelR.establecerRespuesta(rateValue.toString())
            }
            //  viewModelR.rellenar()

            contador++
            if(contador>=viewModel.todas.value!!.size){
                viewModelR.rellenar()

                view!!.findNavController().navigate(R.id.action_encuesta_to_resultado)


            }else{

                bindingEncuesta.preguntas.text=lista.get(contador).name
                if (lista.get(contador).tipoPregunta=="Rating"){
                    bindingEncuesta.ratingBar.visibility=View.VISIBLE
                    bindingEncuesta.editText2.visibility=View.GONE
                    bindingEncuesta.editText3.visibility=View.GONE

                }else if (lista.get(contador).tipoPregunta.equals("Numero")){
                    bindingEncuesta.ratingBar.visibility=View.GONE
                    bindingEncuesta.editText2.visibility=View.GONE
                    bindingEncuesta.editText3.visibility=View.VISIBLE
                    //viewModelR.establecerRespuesta(bindingEncuesta.editText3.text.toString())

                }else{
                    bindingEncuesta.ratingBar.visibility=View.GONE
                    bindingEncuesta.editText2.visibility=View.VISIBLE
                    bindingEncuesta.editText3.visibility=View.GONE
                    // viewModelR.establecerRespuesta(bindingEncuesta.editText2.text.toString())

                }

            }


        }

        return bindingEncuesta.root
    }
}






























//if(ratingShow>=1){
//                //set the rating
//                viewModelR.endSurvey()//increment the value for the survey
//                var rateValue=bindingEncuesta.ratingBar.rating
//
//                viewModelR.rating(rateValue)//get the rating for the ratinbar
//                viewModelR.establecerRespuesta(rateValue.toString())//add to the list of answers
//
//                //navigate to results
//              //  view!!.findNavController().navigate(R.id.action_encuesta_to_resultado)
//            }else if(viewModel.preguntas.value==null){
//               view!!.findNavController().navigate(R.id.action_encuesta_to_resultado)
//                bindingEncuesta.ratingBar.visibility=View.VISIBLE
//                bindingEncuesta.editText2.visibility=View.GONE
//            }
//            viewModelR.establecerRespuesta(bindingEncuesta.editText2.text.toString())
//            viewModel.obtenerPregunta()
//            bindingEncuesta.editText2.setText("")//Clean the edit text
