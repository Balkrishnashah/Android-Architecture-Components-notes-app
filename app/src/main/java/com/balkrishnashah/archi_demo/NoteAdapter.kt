package com.balkrishnashah.archi_demo

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val context: Context, val listener: INotesAdapter) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var dataList:List<Note> ?= null


    val allNotes = ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_layout, parent,false))

        viewHolder.viewBtn.setOnClickListener{
            listener.onViewClicked(allNotes[viewHolder.adapterPosition])
        }
        viewHolder.deleteBtn.setOnClickListener{
            listener.onDeleteClicked(allNotes[viewHolder.adapterPosition])
        }

        viewHolder.editBtn.setOnClickListener{
            listener.onEditClicked(allNotes[viewHolder.adapterPosition])
        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        val currentNote = allNotes[position]
        holder.textView.text = currentNote.note_title

    }

    override fun getItemCount(): Int {
       return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView = itemView.findViewById<TextView>(R.id.note_title)
        val deleteBtn = itemView.findViewById<ImageView>(R.id.note_delete)
        val editBtn = itemView.findViewById<ImageView>(R.id.note_edit)
        val viewBtn = itemView.findViewById<CardView>(R.id.note_item_layout)

    }
}

interface INotesAdapter{
    fun onDeleteClicked(note: Note)
    fun onEditClicked(note: Note)
    fun onViewClicked(note: Note)
}