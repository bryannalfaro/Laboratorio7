package com.example.laboratorio7.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.laboratorio7.R
import com.example.laboratorio7.database.PreguntaEntity
import com.example.laboratorio7.databinding.FragmentPrincipalBinding
import com.example.laboratorio7.viewmodels.PrincipalViewModel

/**
 * @author Bryann
 * Fragment that displays the main view
 */
class Principal : Fragment() {
    private lateinit var viewModel: PrincipalViewModel
    private lateinit var list:List<PreguntaEntity>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val bindingFirst= DataBindingUtil.inflate<FragmentPrincipalBinding>(inflater,R.layout.fragment_principal,container,false)

        viewModel= ViewModelProviders.of(this).get(PrincipalViewModel::class.java)

        viewModel.list.observe(this, Observer {
            list=it
        })
        //navigate to preguntas
        bindingFirst.floatingActionButton.setOnClickListener{
            view!!.findNavController().navigate(R.id.action_principal_to_preguntas)
        }

        //navigate to encuestas with bundle of the question
        bindingFirst.button.setOnClickListener {
            val string: String? = arguments?.getString("question")
            var bundle1= bundleOf("question" to string)
            view!!.findNavController().navigate(R.id.action_principal_to_encuesta,bundle1)
        }
        return bindingFirst.root
    }


}