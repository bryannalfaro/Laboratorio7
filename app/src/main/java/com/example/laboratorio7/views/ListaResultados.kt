package com.example.laboratorio7.views

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.laboratorio7.R
import com.example.laboratorio7.database.SurveyDatabase
import com.example.laboratorio7.databinding.ListaResultadosFragmentBinding
import com.example.laboratorio7.viewmodels.*

/**
 * @author Bryann
 * Fragment for the List of Results
 */
class ListaResultados : Fragment() {

    companion object {
        fun newInstance() = ListaResultados()
    }

    private lateinit var viewModel: ResultadoViewModel
    private lateinit var bindingList:ListaResultadosFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingList=DataBindingUtil.inflate(inflater,R.layout.lista_resultados_fragment,container,false)
        val application= requireNotNull(this.activity).application
        val dataSource= SurveyDatabase.getInstance(application).surveyDao

        //Factory for the list
        val Resultados= ResultadoViewModelFactory(dataSource,application)
        viewModel = ViewModelProviders.of(activity!!,Resultados).get(ResultadoViewModel::class.java)
        bindingList.Texto.setText(viewModel.returnAll().toString())

        return bindingList.root
    }



}
