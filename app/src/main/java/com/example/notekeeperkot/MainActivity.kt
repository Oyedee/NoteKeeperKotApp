package com.example.notekeeperkot

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import com.example.notekeeperkot.databinding.ActivityMainBinding
import com.example.notekeeperkot.databinding.ContentMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding1: ContentMainBinding


    //let's create a private mutable property for our note position
    //which will be received from the intent passed from the NoteListActivity
    private var notePosition = POSITION_NOT_SET // set it's initial value to note position not set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding1 = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        //Let's populate our spinner from our DataManager class
        //we don't need to create an instance of our DataManager, because it is no longer a class but an object
        val adapterCourses = ArrayAdapter<CourseInfo>(
            this,
            android.R.layout.simple_spinner_item,
            DataManager.courses.values.toList()
        )
        adapterCourses.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
//        spinnerCourses = findViewById<Spinner>(R.id.spinner_courses)
        val spinnerCourses = findViewById<Spinner>(R.id.spinner_courses)
        spinnerCourses.adapter = adapterCourses

        //now let's get the note position from the intent that start this activity
        notePosition = intent.getIntExtra(EXTRA_NOTE_POSITION, POSITION_NOT_SET)//return position not set when no position is passed in the intent

        //Let's check to see that our notePosition is not set to POSITION_NOT_SET
        //We will do that using and if statement
        if (notePosition != POSITION_NOT_SET) {
            displayNote()
        } else {
            //let's add the option to create a new note in our DataManager or DataStore
            //which is an empty note
            DataManager.notes.add(NoteInfo())
            //set mainActivity class notePosition property to the lastIndex of the DataManager's notes collection
            notePosition = DataManager.notes.lastIndex
        }

/*       binding.fab.setOnClickListener { view ->
           Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               .setAction("Action", null).show()
                }
 */
    }

    private fun displayNote() {
        //let's get the note that corresponds to the passed notePosition
        var note = DataManager.notes[notePosition] //this gives us our note
        //now let's display the note value on the views in our MainActivity
        val textNoteTitle = findViewById<EditText>(R.id.text_note_title)
        val textNoteText = findViewById<EditText>(R.id.text_note_text)

        textNoteTitle.setText(note.title) //displays the title
        textNoteText.setText(note.text) //displays the note

        //now let's display the appropriate course for this note
        //we can do that by selecting the appropriate course from the spinner
        var coursePosition = DataManager.courses.values.indexOf(note.course)
        val spinnerCourses = findViewById<Spinner>(R.id.spinner_courses)
        spinnerCourses.setSelection(coursePosition)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true // we need to always return true for any menu item we are handling
            R.id.action_next -> {
                moveNext() //this func will implement moving to the next note
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun moveNext() {
        //first we need to increment our note position
        ++notePosition
        //then we display the incremented note
        displayNote()
        invalidateOptionsMenu() //schedules a call to the onPrepareOptionsMenu
    }

    //let's change the menu item for our Next action when the user gets to the last note
    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        //let's add an if statement to check if our note position is at the last index
        if (notePosition >= DataManager.notes.lastIndex) {
            //find the menu item we want to make changes to
            val menuItem = menu?.findItem(R.id.action_next) //we are accessing our menu using the safe call operator
            //now let's use our menuItem in a null safe way
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_baseline_block_24) //change menu item icon for the 'Next' action
                menuItem.isEnabled = false //disable the menuItem
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    //Let's add a save capability to our Activity when the user makes changes to the note
    override fun onPause() {
        super.onPause()
        saveNote() //save note function
    }

    private fun saveNote() {
        //let's save the content of the note from the screen into our DataManager
        val note = DataManager.notes[notePosition] //get note from current note position
        val spinnerCourses = findViewById<Spinner>(R.id.spinner_courses)
        val textNoteTitle = findViewById<EditText>(R.id.text_note_title)
        val textNoteText = findViewById<EditText>(R.id.text_note_text)
        note.title = textNoteTitle.text.toString()//save the content of title to the DataManager
        note.text = textNoteText.text.toString()//save the content of text to the DataManager
        note.course = spinnerCourses.selectedItem as CourseInfo //save the content of the spinner into the DataManager
    }
}