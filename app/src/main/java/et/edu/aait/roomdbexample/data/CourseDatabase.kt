package et.edu.aait.roomdbexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Course::class), version = 1)
abstract class CourseDatabase: RoomDatabase() {
    abstract fun courseDao():CourseDao

    companion object {

        @Volatile
        private var INSTANCE: CourseDatabase? = null

        fun getDatabase(context: Context): CourseDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CourseDatabase::class.java, "course_database"
                ).build()

                INSTANCE = instance
                return instance
            }

        }
    }
}

