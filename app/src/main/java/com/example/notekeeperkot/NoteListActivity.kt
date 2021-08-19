package com.example.notekeeperkot

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeperkot.databinding.ActivityNoteListBinding
import com.example.notekeeperkot.databinding.ContentNoteListBinding

class NoteListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoteListBinding
    private lateinit var binding1: ContentNoteListBinding

    var listNotes: ListView? = null

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

        //let's implement a click listener for our listView
        //this will execute when the user selects a note item from the listView
        listNotes.setOnItemClickListener{parent, view, position, id ->
            val activityIntent = Intent(this, MainActivity::class.java)
            activityIntent.putExtra(NOTE_POSITION, position)
            startActivity(activityIntent)
        }
    }


    /***
     * Let's notify our NoteListActivity via the listView that the set of data has changed
     * after we added a new note to the list
     * We are going to do that using the onResume method
     * then we access the adapter used to populate the listView
     * cast the adapter to an arrayAdapter because it is a base adapter
     * then we call the method to inform the ArrayAdapter that the underlying data has changed
     */
    override fun onResume() {
        super.onResume()
        listNotes = findViewById(R.id.list_notes)
        (listNotes?.adapter as ArrayAdapter<NoteInfo>).notifyDataSetChanged()
    }


}