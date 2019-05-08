package et.edu.aait.roomdbexample.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "courses")
data class Course(
    @PrimaryKey @ColumnInfo(name = "course_code") val code:String,
    @ColumnInfo(name = "course_title") val title:String,
    @ColumnInfo(name = "course_ects") val ects:Int,
    @ColumnInfo(name = "course_description") val description:String
)

