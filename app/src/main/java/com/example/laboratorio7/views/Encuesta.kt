package com.example.laboratorio7.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.laboratorio7.R
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
    private var ratingShow:Int=0



    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Instantiate the application and the DataBase
        val application= requireNotNull(this.activity).application
        val dataSource= SurveyDatabase.getInstance(application).surveyDao

        //Make the factories
        val encuestaFactory= EncuestaViewModelFactory(dataSource,application)
        val respuestaFactory= ResultadoViewModelFactory(dataSource,application)

        //inflate the view
        bindingEncuesta = DataBindingUtil.inflate(inflater, R.layout.encuesta_fragment, container, false)

        //init the viewModel
        viewModel = ViewModelProviders.of(activity!!,encuestaFactory).get(EncuestaViewModel::class.java)
        viewModelR=ViewModelProviders.of(activity!!,respuestaFactory).get(ResultadoViewModel::class.java)
        bindingEncuesta.encuestaModel=viewModel


        bindingEncuesta.lifecycleOwner=viewLifecycleOwner

        //Init the info in the view
        viewModel.makePregunta()
        viewModel.selectPregunta()

        //next question
        bindingEncuesta.button2.setOnClickListener {

            if(ratingShow>=1){
                //set the rating

                viewModelR.endSurvey()//increment the value for the survey
                //get the rating for the ratinbar
                var rateValue=bindingEncuesta.ratingBar.rating
                Toast.makeText(activity,"$rateValue",Toast.LENGTH_SHORT).show()

                viewModelR.rating(rateValue)
                viewModelR.establecerRespuesta(rateValue.toString())//add to the list of answers

                //navigate to results
                view!!.findNavController().navigate(R.id.action_encuesta_to_resultado)
            }else if(viewModel.preguntasList.size<=1){
                ratingShow=1
                bindingEncuesta.ratingBar.visibility=View.VISIBLE
                bindingEncuesta.editText2.visibility=View.GONE
            }
            viewModelR.establecerRespuesta(bindingEncuesta.editText2.text.toString())
            viewModel.selectPregunta()
            bindingEncuesta.editText2.setText("")//Clean the edit text
        }
        return bindingEncuesta.root
    }
}
