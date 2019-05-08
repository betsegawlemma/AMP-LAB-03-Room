package et.edu.aait.roomdbexample

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import et.edu.aait.roomdbexample.data.Course
import et.edu.aait.roomdbexample.data.CourseDao
import et.edu.aait.roomdbexample.data.CourseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var addButton: Button
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button
    private lateinit var searchButton: Button

    private lateinit var titleEditText: EditText
    private lateinit var codeEditText: EditText
    private lateinit var ectsEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var searchEditText: EditText

    private lateinit var courseDatabase: CourseDatabase
    private lateinit var courseDao: CourseDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AsyncTask.execute{
            courseDatabase = CourseDatabase.getDatabase(this)
            courseDao = courseDatabase.courseDao()
        }

        addButton = add_button
        deleteButton = delete_button
        updateButton = update_button
        searchButton = search_button

        titleEditText = title_edit_text
        codeEditText = code_edit_text
        descriptionEditText = description_edit_text
        ectsEditText = ects_edit_text
        searchEditText = search_edit_text

        addButton.setOnClickListener {
            val course = readFields()
            AsyncTask.execute {
                saveCourse(course)
            }
            clearFields()
        }

        updateButton.setOnClickListener {
            val course = readFields()
            AsyncTask.execute {
                updateCourse(course)
            }
            clearFields()
        }

        deleteButton.setOnClickListener {
            val course = readFields()
            AsyncTask.execute {
                deleteCourse(course)
            }
            clearFields()
        }

      searchButton.setOnClickListener {
          val code = searchEditText.text.toString()
          AsyncTask.execute {
              val course = searchCourse(code)
              runOnUiThread {
                  updateFields(course)
              }
          }
      }
    }

    private fun updateFields(course: Course){
        if(course!=null) {
            codeEditText.setText(course.code)
            titleEditText.setText(course.title)
            ectsEditText.setText(course.ects.toString())
            descriptionEditText.setText(course.description)
        }
    }

    private fun readFields():Course{
        return Course(
            codeEditText.text.toString(),
            titleEditText.text.toString(),
            ectsEditText.text.toString().toInt(),
            descriptionEditText.text.toString()
        )
    }

    private fun clearFields() {
        searchEditText.setText("")
        codeEditText.setText("")
        titleEditText.setText("")
        ectsEditText.setText("")
        descriptionEditText.setText("")
    }
    private fun updateCourse(course: Course){
        courseDao.updateCourse(course)
    }

    private fun saveCourse(course: Course){
        courseDao.insertCourse(course)
    }

    private fun deleteCourse(course: Course){
        courseDao.deleteCourse(course)
    }

    private fun searchCourse(code: String):Course{
        return courseDao.getCourseByCode(code)
    }
}

