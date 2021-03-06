package com.example.notekeeperkot

import android.provider.ContactsContract

//making our DataManager class a singleton by replacing the class keyword to 'object' keyword in our class declaration
object DataManager {
    val courses = HashMap<String, CourseInfo>() //list of courses
    val notes = ArrayList<NoteInfo>() //list of notes

    //initialization block which will run our initializeCourses function
    // when an instance of our DataManager class is called.
    init {
        initializeCourses()
        initializeNotes()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android programming with Intents")
        courses.set(course.courseId, course) //set the content of our CourseInfo instance to the HashMap

        course = CourseInfo("android_async", "Android Async programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo("kotlin_lang", "Kotlin Fundamentals: The Kotlin Language")
        courses.set(course.courseId, course)

        course = CourseInfo("kotlin_core", "Kotlin Fundamentals: The Core Platform")
        courses.set(course.courseId, course)
    }

    private fun initializeNotes() {
        var course = CourseInfo("android_intents", "Android Programming with intents")
        var note = NoteInfo(course, "Android Programming with intents", "Make your app robust by linking with multiple activities")
        notes.add(note)

        course = CourseInfo("android_async", "Android Async programming and services" )
        note = NoteInfo(course, "Long running operations", "Foreground Services can be tied to a notification icon")
        notes.add(note)

        course = CourseInfo("kotlin_lang", "The Kotlin Language" )
        note = NoteInfo(course, "Parameters", "Leverage variable-length parameter lists")
        notes.add(note)
    }
}