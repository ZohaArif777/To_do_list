package com.example.to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateCard : AppCompatActivity() {
    private lateinit var database: myDb
    private lateinit var createTitle: EditText
    private lateinit var createPriority: EditText
    private lateinit var deleteButton: Button
    private lateinit var updateButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_card)

        createTitle = findViewById(R.id.create_title)
        createPriority = findViewById(R.id.create_priority)
        deleteButton = findViewById(R.id.delete_button)
        updateButton = findViewById(R.id.update_button)

        database = Room.databaseBuilder(
            applicationContext, myDb::class.java, "To_Do"
        ).build()

        val pos = intent.getIntExtra("id", -1)
        if (pos != -1) {
            val title = DataObject.getData(pos).title // Assuming DataObject has a getData method
            val priority = DataObject.getData(pos).priority
            createTitle.setText(title)
            createPriority.setText(priority)

            deleteButton.setOnClickListener {
                DataObject.deleteData(pos) // Assuming DataObject has a deleteData method
                GlobalScope.launch {
                    database.dao().deleteTask(Entity(pos + 1, title, priority)) // Use Entity instead of Entityy
                }
                myIntent()
            }

            updateButton.setOnClickListener {
                DataObject.updateData(
                    pos,
                    createTitle.text.toString(),
                    createPriority.text.toString()
                ) // Assuming DataObject has an updateData method
                GlobalScope.launch {
                    database.dao().updateTask(Entity(pos + 1, createTitle.text.toString(), createPriority.text.toString())) // Use Entity instead of Entityy
                }
                myIntent()
            }
        }
    }

    private fun myIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
