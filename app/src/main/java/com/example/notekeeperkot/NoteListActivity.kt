package com.example.notekeeperkot

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeperkot.databinding.ActivityNoteListBinding
import com.example.notekeeperkot.databinding.ContentNoteListBinding

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding
    private lateinit var binding1: ContentNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        binding1 = ContentNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        binding.fab.setOnClickListener { view ->
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }

        //Let's add the code to populate our listView
        val listNotes = findViewById<ListView>(R.id.list_notes)
        listNotes.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            DataManager.notes
        )
    }


}