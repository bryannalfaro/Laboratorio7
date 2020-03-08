package com.example.laboratorio7.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio7.R
import com.example.laboratorio7.databinding.PruebaRecyclerFragmentBinding
import com.example.laboratorio7.viewmodels.ResultadoViewModel

class PruebaRecycler : Fragment() {


    companion object {
        fun newInstance() = PruebaRecycler()
    }

    private lateinit var viewModel: ResultadoViewModel
    private lateinit var bindin: PruebaRecyclerFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindin= DataBindingUtil.inflate(inflater,
            R.layout.prueba_recycler_fragment,container,false)
        var recycler: RecyclerView

        viewModel= ViewModelProviders.of(activity!!).get(ResultadoViewModel::class.java)


        recycler=bindin.recycler
        var adapter= Adapter(context!!)
        recycler.adapter=adapter
        recycler.layoutManager= LinearLayoutManager(context)


        adapter.setQuestions(viewModel.supremo)


        return bindin.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!).get(ResultadoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}