package com.balkrishnashah.archi_demo

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class NoteViewModel(application: Application) : AndroidViewModel(application){

    private val repository : NoteRepository
    val allNotes  :LiveData<List<Note>>

    init
    {
        val dao = NoteRoomDatabase.getDatabase(application).getNoteDao()
        repository =  NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun insertNode(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
    fun deleteNode(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }
    fun update(note_data:String, noteId:Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateNote(note_data, noteId)
    }

    fun deleteAllNote() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}







//class NoteViewModel(private val repository: NoteRepository) : ViewModel() {
//
//    // Using LiveData and caching what allNotes returns has several benefits:
//    // - We can put an observer on the data (instead of polling for changes) and only update the
//    //   the UI when the data actually changes.
//    // - Repository is completely separated from the UI through the ViewModel.
//    val allNotes: LiveData<List<Note>> = repository.allNotes
//
//    /**
//     * Launching a new coroutine to insert the data in a non-blocking way
//     */
//    fun insert(word: Note) = viewModelScope.launch {
//        repository.insert(word)
//    }
//}
//
//
//class NoteViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return NoteViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}