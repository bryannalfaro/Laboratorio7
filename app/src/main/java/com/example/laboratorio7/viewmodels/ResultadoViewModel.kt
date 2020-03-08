package com.example.laboratorio7.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.laboratorio7.database.SurveyDao
import kotlinx.coroutines.*

/**
 * @author Bryann
 * ViewModel for the results
 */
class ResultadoViewModel  (val database: SurveyDao, application: Application): AndroidViewModel(application) {


    private var viewModelJob= Job()
    private val  uiScope= CoroutineScope(Dispatchers.Main+viewModelJob)
    var encuestas=database.getNumberOfSurveys()


    var cantidadSurveys:Int=0
    var promedioRating:Float=0.0f
    var respuestas=ArrayList<String>()
    var supremo=ArrayList<ArrayList<String>>()
    var ratingbarsa:Float= 0.0f
    init {
        numero()
    }

    fun rellenar(){
        supremo.add(respuestas)
    }

    fun numero(){
        uiScope.launch {
            traerNumero()
        }
    }

    suspend fun traerNumero(){
        withContext(Dispatchers.IO){
            var list=database.getNumberOfSurveys()
            list=encuestas
        }
    }


    fun endSurvey(){
        cantidadSurveys++
    }
    fun rating(ratingbar:Float){
        ratingbarsa=ratingbarsa+ratingbar

        promedioRating=((ratingbarsa/cantidadSurveys))
    }

    fun returnAll():ArrayList<String>{
        return respuestas
    }

    fun establecerRespuesta(respuesta:String){
        respuestas.add(respuesta)
    }


}
