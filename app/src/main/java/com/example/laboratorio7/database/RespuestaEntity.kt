package com.example.laboratorio7.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

/**
 * @author Bryann Alfaro
 * Entidad para las respuestas
 */
@Entity(tableName = "answer_table", foreignKeys = [ForeignKey(entity =EncuestaEntity::class,
    parentColumns = ["id"], childColumns = ["pool_id"], onDelete = CASCADE),
    ForeignKey(entity =PreguntaEntity::class, parentColumns = ["id"], childColumns = ["question_id"],
        onDelete = CASCADE)])

data class RespuestaEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,
    @ColumnInfo(name = "pool_id",index = true)
    var encuestaId:Long=0L,
    @ColumnInfo(name = "question_id",index = true)
    var questionId:Long=0L,
    @ColumnInfo(name = "question_text",index = true)
    var questionText:String,
    @ColumnInfo(name = "question_number",index = true)
    var questionNumber:Long=0L
)

