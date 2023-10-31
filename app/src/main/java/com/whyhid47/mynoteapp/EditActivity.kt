package com.whyhid47.mynoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.whyhid47.mynoteapp.databinding.ActivityEditBinding


class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding
    private lateinit var db: NoteSQLDBHeper
    private var noteId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteSQLDBHeper(this)
        noteId = intent.getIntExtra("id", -1)
        if (noteId == -1) {
            finish()
            return
        }
        val note = db.getNoteById(noteId)
        binding.addTitleEditText.setText(note.title)
        binding.addContentEditText.setText(note.content)

        // update
        binding.updateButton.setOnClickListener {
            val newTitle = binding.addTitleEditText.text.toString()
            val newContent = binding.addContentEditText.text.toString()
            val updatedNote = Note(noteId, newTitle, newContent)
            //check data
            if (newTitle.isEmpty()){
                Toast.makeText(this, "Please check title", Toast.LENGTH_SHORT).show()
            }else if (newContent.isEmpty()){
                Toast.makeText(this, "Please check content", Toast.LENGTH_SHORT).show()
            }else{
                db.updateNote(updatedNote)
                db.close()
                finish()
                Toast.makeText(this, "Note has been Updated", Toast.LENGTH_SHORT).show()
            }

        }
    }
}