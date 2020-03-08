package com.example.laboratorio7.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.laboratorio7.database.SurveyDao

/**
 * @author Bryann Alfaro
 * Factory for the results
 */
class ResultadoViewModelFactory(
    private val dataSource: SurveyDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultadoViewModel::class.java)) {
            return ResultadoViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}