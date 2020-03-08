package com.example.laboratorio7.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio7.R

/**
 * Adapter for the Recycler view
 */
class Adapter internal constructor(context: Context): RecyclerView.Adapter<Adapter.ViewHolderData>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemView=inflater.inflate(R.layout.list_recycler,parent,false)
        return ViewHolderData((itemView))
    }

    override fun getItemCount()=PreguntasList.size

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val pregunta=PreguntasList[position]

        holder.questionItenView.text=pregunta.get(0)
        holder.pregunta.text=pregunta.get(1)
    }

    internal fun setQuestions(preguntas: ArrayList<ArrayList<String>>){
        this.PreguntasList=preguntas

        notifyDataSetChanged()
    }

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var PreguntasList= ArrayList<ArrayList<String>>()

    //ViewHolder for the data
    inner class ViewHolderData(itemView: View): RecyclerView.ViewHolder(itemView){
        val questionItenView: TextView =itemView.findViewById(R.id.idPregunta)
        val pregunta: TextView =itemView.findViewById(R.id.pregunta)
    }



}