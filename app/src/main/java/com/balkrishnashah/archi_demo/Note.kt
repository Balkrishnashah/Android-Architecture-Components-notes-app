package com.balkrishnashah.archi_demo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note( @ColumnInfo(name = "title") val note_title:String) {
    @PrimaryKey(autoGenerate = true) var id:Int = 0

}