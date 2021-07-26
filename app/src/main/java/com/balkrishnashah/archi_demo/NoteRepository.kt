package com.balkrishnashah.archi_demo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData


class NoteRepository(private val noteDao: NoteDao) {


    val allNotes: LiveData<List<Note>> = noteDao.fetchAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }

    suspend fun updateNote(notedata:String, noteID:Int) {
        noteDao.updateNote(notedata, noteID)
    }

    suspend fun deleteAll() {
        noteDao.deleteAll()
    }


}