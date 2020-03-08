package com.example.laboratorio7.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Bryann Alfaro
 * Entidad para las preguntas
 */
@Entity(tableName = "question_table")
data class PreguntaEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo(name = "name")
    var name:String="",
    @ColumnInfo(name = "type") //Si es texto o numero
    var tipoPregunta:String="",
    @ColumnInfo(name= "default")  //si pregunta establecida o no
    var defect:Int=1

)