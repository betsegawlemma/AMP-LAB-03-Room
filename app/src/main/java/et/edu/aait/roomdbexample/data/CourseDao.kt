package et.edu.aait.roomdbexample.data

import androidx.room.*

@Dao
interface CourseDao {

    @Query("SELECT * FROM courses WHERE course_code = :code LIMIT 1")
    fun getCourseByCode(code:String):Course

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: Course):Long

    @Update
    fun updateCourse(course: Course):Int

    @Delete
    fun deleteCourse(course: Course):Int

}

