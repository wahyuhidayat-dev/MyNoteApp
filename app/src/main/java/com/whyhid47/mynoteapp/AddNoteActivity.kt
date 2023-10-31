package com.whyhid47.mynoteapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.whyhid47.mynoteapp.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddNoteBinding
    private  lateinit var db: NoteSQLDBHeper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db= NoteSQLDBHeper(this)
        binding.addButton.setOnClickListener{
            val title = binding.addTitleEditText.text.toString()
            val content = binding.addContentEditText.text.toString()
            val note = Note(0,title,content)
            if (title.isEmpty()){
                Toast.makeText(this, "Please input title", Toast.LENGTH_SHORT).show()
            }else if (content.isEmpty()){
                Toast.makeText(this, "Please input content", Toast.LENGTH_SHORT).show()
            }else {
                db.insertNote(note)
                finish()
                Toast.makeText(this, "Note has been added", Toast.LENGTH_SHORT).show()
            }
        }

    }
}