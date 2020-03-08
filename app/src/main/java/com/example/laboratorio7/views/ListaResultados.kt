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

    private lateinit var viewModel: ListaResultadosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.lista_resultados_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}

