package com.example.laboratorio7.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.laboratorio7.database.SurveyDao
import com.example.laboratorio7.database.SurveyDatabase
import kotlinx.coroutines.*

class PruebaRecyclerViewModel(application: Application): AndroidViewModel(application) {
    private var viewModelJob= Job()
    private val  uiScope= CoroutineScope(Dispatchers.Main+viewModelJob)
    private val preguntasDao: SurveyDao = SurveyDatabase.getDatabase(application).surveyDao()
    var list=preguntasDao.getAllPreguntas()



    init {
        preguntas()
    }

    fun preguntas(){
        uiScope.launch {
            getPregunta()
        }
    }

    suspend fun getPregunta(){
        withContext(Dispatchers.IO){
            val listS=preguntasDao.getAllPreguntas()
            list=listS

        }
    }
}