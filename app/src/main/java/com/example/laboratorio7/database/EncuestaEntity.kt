package com.example.laboratorio7.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Bryann Alfaro
 * Entidad para crear la encuesta
 */

@Entity(tableName = "poll_table")
data class EncuestaEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long=0L,
    @ColumnInfo(name = "create_date")
    var startTimeMilli:Long = System.currentTimeMillis()
    )