package com.balkrishnashah.archi_demo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {


    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()

    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun fetchAllNotes(): LiveData<List<Note>>

    @Query("UPDATE note_table SET title = :uTitle  WHERE id = :noteIds")
    fun updateNote(uTitle:String, noteIds: Int)

    @Query("SELECT * FROM note_table WHERE id IN (:noteIds)")
    fun loadAllByIds(noteIds: IntArray): LiveData<List<Note>>

    @Query("SELECT * FROM note_table WHERE title LIKE :title LIMIT 1")
    fun findByTitle(title: String): Note

}