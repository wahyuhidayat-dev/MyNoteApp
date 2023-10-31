package com.whyhid47.mynoteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.whyhid47.mynoteapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var db: NoteSQLDBHeper
    private lateinit var notesAdapter : NotesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.floatingButton.setOnClickListener{
            startActivity(Intent(this,AddNoteActivity::class.java))
        }

        db = NoteSQLDBHeper(this)
        notesAdapter= NotesAdapter(db.getAll(),this)

        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = notesAdapter





    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAll())

    }
}