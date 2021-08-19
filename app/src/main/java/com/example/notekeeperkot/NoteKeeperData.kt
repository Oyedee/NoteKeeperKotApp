package com.example.notekeeperkot

data class CourseInfo (val courseId: String, val title: String) {
    override fun toString(): String {
        return title
    }
}


/**
 * Let's make our Note Info Class Nullable
 * So what that would allow us to do is create a new instance of NoteInfo and pass null for each of
 * these properties
 * Also, in addition let's give the properties a default value of null**/
data class NoteInfo (var course: CourseInfo? = null, var title: String? = null, var text: String? = null)