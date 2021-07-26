package com.balkrishnashah.archi_demo

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.InternalCoroutinesApi


@InternalCoroutinesApi
class MainActivity : AppCompatActivity(), INotesAdapter {


    lateinit var viewModel: NoteViewModel
    lateinit var cc_layout: ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        cc_layout = findViewById(R.id.main_layout)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NoteAdapter(this, this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java)

        viewModel.allNotes.observe(this, Observer { list ->
            list?.let {
                adapter.updateList(it)
            }

        })





    }

    override fun onDeleteClicked(note: Note) {
        viewModel.deleteNode(note)
//        Toast.makeText(this, "${note.note_title} deleted", Toast.LENGTH_SHORT).show()
        val snack = Snackbar.make(cc_layout, "${note.note_title} deleted", Snackbar.LENGTH_SHORT)
        snack.show()

    }

    override fun onEditClicked(note: Note) {

        val inflater = LayoutInflater.from(applicationContext).inflate(R.layout.update_note_layout, null)
        val edit_note_text = inflater.findViewById<EditText>(R.id.update_note_title)


        edit_note_text.text = Editable.Factory.getInstance().newEditable(note.note_title)
        
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Update Data")
        alertDialog.setView(inflater)
        alertDialog.setCancelable(false)

        alertDialog.setNegativeButton("Cancel"){ dialog, which->
            val snack = Snackbar.make(cc_layout, "cancelled", Snackbar.LENGTH_SHORT)
            snack.show()
        }

        alertDialog.setPositiveButton("Update"){dialog,which->
            val text = edit_note_text.text.toString().trim()

            if (text.isNotEmpty()){
                viewModel.update(text, note.id)
            }

            val snack = Snackbar.make(cc_layout, "updated ", Snackbar.LENGTH_SHORT)
            snack.show()
        }

        val dialog = alertDialog.create()
        dialog.show()
    }

    override fun onViewClicked(note: Note) {
//        future implementation
    }

    fun onAddNote(view: View) {
        val note_edit_text = findViewById<EditText>(R.id.note_text)
        val noteText = note_edit_text.text.toString().trim()
        if (noteText.isNotEmpty()){
            viewModel.insertNode(Note(noteText))
            note_edit_text.text = null

            val snack = Snackbar.make(cc_layout, "${noteText} added", Snackbar.LENGTH_SHORT)
            snack.show()
        }
    }

    fun onDeleteAll(view: View) {
        viewModel.deleteAllNote()
        val snack = Snackbar.make(cc_layout, "all notes Deleted", Snackbar.LENGTH_SHORT)
        snack.show()

    }





}