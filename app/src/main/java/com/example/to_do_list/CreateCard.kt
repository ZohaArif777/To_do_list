package com.example.to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CreateCard : AppCompatActivity() {
    private lateinit var database: myDb
    private lateinit var saveButton: Button
    private lateinit var createTitle: EditText
    private lateinit var createPriority: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_card)

        saveButton = findViewById(R.id.save_button)
        createTitle = findViewById(R.id.create_title)
        createPriority = findViewById(R.id.create_priority)

        database = Room.databaseBuilder(
            applicationContext, myDb::class.java, "To_Do"
        ).build()

        saveButton.setOnClickListener {
            val title = createTitle.text.toString().trim()
            val priority = createPriority.text.toString().trim()

            if (title.isNotEmpty() && priority.isNotEmpty()) {
                GlobalScope.launch {
                    database.dao().insertTask(Entity(0, title, priority)) // Use Entity instead of Entityy
                }
                DataObject.setData(title, priority) // Assuming DataObject has a setData method
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
