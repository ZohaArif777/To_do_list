package com.example.to_do_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var database: myDb
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: Button
    private lateinit var deleteAllButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recycler_view)
        addButton = findViewById(R.id.add)
        deleteAllButton = findViewById(R.id.deleteAll)
        database = Room.databaseBuilder(applicationContext, myDb::class.java, "To_Do").build()
        addButton.setOnClickListener {
            val intent = Intent(this, CreateCard::class.java)
            startActivity(intent)
        }
        deleteAllButton.setOnClickListener {
            DataObject.deleteAll()
            GlobalScope.launch {
                database.dao().deleteAll()
            }
            setRecycler()
        }

        setRecycler()
    }

    private fun setRecycler() {
        // Get data from DataObject and set adapter
        val data = DataObject.getAllData()
        recyclerView.adapter = Adapter(data)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}
