package com.example.laboratorio7.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.*

/**
 * @author Bryann Alfaro
 * Database
 */
@Database(entities = [EncuestaEntity::class,PreguntaEntity::class,RespuestaEntity::class],version = 1,exportSchema = false)
abstract class SurveyDatabase:RoomDatabase (){
    abstract fun surveyDao():SurveyDao

    private class WordDatabaseCallback: RoomDatabase.Callback() {    private var viewModelJob= Job()
        val uiScope= CoroutineScope(Dispatchers.Main+viewModelJob)

        //OnOpen populate database
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            INSTANCE?.let { database ->
                uiScope.launch {

                    // Add words to database
                    populateData(database.surveyDao())

                }
            }
        }

        suspend fun populateData(dao:SurveyDao){
            withContext(Dispatchers.IO){
                if (dao.getCantidadPreguntas()==0){
                    var word = PreguntaEntity()
                    word.name="¿Tiene algun comentario o sugerencia?"
                    word.tipoPregunta="Texto"
                    dao.insertPregunta(word)
                    var word2 = PreguntaEntity()
                    word2.name="¿Qué le parecio nuestro servicio?"
                    word2.tipoPregunta="Rating"
                    dao.insertPregunta(word2)
                }
            }
        }
    }





    companion object {
        @Volatile
        private var INSTANCE: SurveyDatabase? = null

        fun getDatabase(
            context: Context
        ): SurveyDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SurveyDatabase::class.java,
                    "word_database"
                )
                    .addCallback(WordDatabaseCallback())
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


    }}