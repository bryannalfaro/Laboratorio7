package com.example.laboratorio7.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.laboratorio7.R

class Adapter internal constructor(context: Context): RecyclerView.Adapter<Adapter.ViewHolderData>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        val itemView=inflater.inflate(R.layout.list_recycler,parent,false)
        return ViewHolderData((itemView))
    }

    override fun getItemCount()=questions.size

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        val pregunta=questions[position]
//        var contador1=0
//        var contador2=1

        holder.questionItenView.text=pregunta.get(0)
//        contador1=contador1+2

        holder.pregunta.text=pregunta.get(1)
//        contador2=contador2+2
//        questions.removeAt(0)
//        questions.removeAt(1)

    }

    internal fun setQuestions(preguntas: ArrayList<ArrayList<String>>){
        this.questions=preguntas

        notifyDataSetChanged()
    }

    private var inflater: LayoutInflater = LayoutInflater.from(context)
    private var questions= ArrayList<ArrayList<String>>()
    private var surveys=0

    inner class ViewHolderData(itemView: View): RecyclerView.ViewHolder(itemView){
        val questionItenView: TextView =itemView.findViewById(R.id.idPregunta)
        val pregunta: TextView =itemView.findViewById(R.id.pregunta)
    }



}