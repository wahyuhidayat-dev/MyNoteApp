package com.whyhid47.mynoteapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notesList: List<Note>, context: Context) :
RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val  db:NoteSQLDBHeper = NoteSQLDBHeper(context)
    class NoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val titleTextView : TextView = itemView.findViewById(R.id.note_sample_title)
        val contentTextView : TextView = itemView.findViewById(R.id.note_sample_content)
        val updateButton : ImageView = itemView.findViewById(R.id.note_sample_update_button)
        val deleteButton :ImageView = itemView.findViewById(R.id.note_sample_delete_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesAdapter.NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_sample,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NoteViewHolder, position: Int) {
        val note = notesList[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content

        // update
        holder.updateButton.setOnClickListener{
            val intent = Intent(holder.itemView.context, EditActivity::class.java).apply {
                putExtra("id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        // delete button
        holder.deleteButton.setOnClickListener{
            db.deleteNote(note.id)
            refreshData(db.getAll())
            Toast.makeText(holder.itemView.context, "Note has been deleted", Toast.LENGTH_LONG).show()
        }
        
    }

    override fun getItemCount(): Int  = notesList.size
    
    fun refreshData(newNotes: List<Note>){
        notesList = newNotes
        notifyDataSetChanged()
    }

}