package com.example.notekeeperkot

class DataManager {
    val courses = HashMap<String, CourseInfo>() //list of courses
    val notes = ArrayList<NoteInfo>() //list of notes

    //initialization block which will run our initializeCourses function
    // when an instance of our DataManager class is called.
    init {
        initializeCourses()
    }

    private fun initializeCourses() {
        var course = CourseInfo("android_intents", "Android programming with Intents")
        courses.set(course.courseId, course) //set the content of our CourseInfo instance to the HashMap

        course = CourseInfo("android_async", "Android Async programming and Services")
        courses.set(course.courseId, course)

        course = CourseInfo("kotlin_lang", "Kotlin Fundamentals: The Java Language")
        courses.set(course.courseId, course)

        course = CourseInfo("kotlin_core", "Kotlin Fundamentals: The Core Platform")
        courses.set(course.courseId, course)
    }
}