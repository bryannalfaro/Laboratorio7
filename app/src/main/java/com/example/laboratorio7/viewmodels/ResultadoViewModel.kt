package com.example.laboratorio7.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.laboratorio7.database.SurveyDao

/**
 * @author Bryann
 * ViewModel for the results
 */
class ResultadoViewModel  (val database: SurveyDao, application: Application): AndroidViewModel(application) {


     var cantidadSurveys:Float=0.0f
     var promedioRating:Float=0.0f
    var ratingbarsa:Float=0.0f
    private var respuestas=ArrayList<String>()


    fun endSurvey(){
        cantidadSurveys++
    }
    fun rating(ratingbar:Float){
        ratingbarsa=ratingbarsa+ratingbar
        promedioRating=((ratingbarsa)/cantidadSurveys)
    }

    fun returnAll():ArrayList<String>{
        return respuestas
    }

    fun establecerRespuesta(respuesta:String){
        respuestas.add(respuesta)
    }


}
