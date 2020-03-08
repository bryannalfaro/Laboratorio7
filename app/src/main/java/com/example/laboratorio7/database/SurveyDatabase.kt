package com.example.laboratorio7.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author Bryann Alfaro
 * Database
 */
@Database(entities = [EncuestaEntity::class,PreguntaEntity::class,RespuestaEntity::class],version = 1,exportSchema = false)
abstract class SurveyDatabase:RoomDatabase (){
    abstract val surveyDao:SurveyDao
    companion object{

        private var INSTANCE:SurveyDatabase?=null


        fun getInstance(context: Context):SurveyDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, SurveyDatabase::class.java, "survey_database").fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                }
                return instance
            }
        }
    }

}