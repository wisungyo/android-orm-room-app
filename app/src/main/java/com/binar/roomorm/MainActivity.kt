package com.binar.roomorm

import android.content.Intent
import android.media.MediaCodec
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var db: ItemDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        this.supportActionBar?.hide()
        ItemDatabase.getInstance(this)?.let {
            db = it
        }

        fab_add.setOnClickListener {
            val intentGoToAddActivity = Intent(this, AddActivity::class.java)
            startActivity(intentGoToAddActivity)
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData() {
        GlobalScope.launch {
            val listItem = db.itemDao().readAllItem()
            runOnUiThread {
                val adapter = ItemAdapter(listItem)
                recycler_view.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
                recycler_view.adapter = adapter
            }
        }
    }
}