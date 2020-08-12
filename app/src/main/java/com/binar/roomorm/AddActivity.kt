package com.binar.roomorm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.room.util.DBUtil
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add.btn_add
import kotlinx.android.synthetic.main.activity_add.et_name
import kotlinx.android.synthetic.main.activity_add.et_quantity
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.lang.NumberFormatException

class AddActivity : AppCompatActivity() {
    private lateinit var db: ItemDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        ItemDatabase.getInstance(this)?.let {
            db = it
        }

        btn_add.setOnClickListener {
            if (et_name.text.isNotEmpty() && et_quantity.text.isNotEmpty()) {
               val item = Item(null, et_name.text.toString(), et_quantity.text.toString().toInt())
               GlobalScope.async {
                   val totalSaved = db.itemDao().addItem(item)
                   runOnUiThread {
                       if (totalSaved > 0) {
                           Toast.makeText(
                               this@AddActivity, "Data saved",
                               Toast.LENGTH_LONG
                           ).show()
                           this@AddActivity.finish()
                       } else {
                           Toast.makeText(
                               this@AddActivity, "Data fail to save",
                               Toast.LENGTH_LONG
                           ).show()
                       }
                   }
               }
            } else {
                Toast.makeText(
                    this@AddActivity, "Field cannot be empty",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        et_name.addTextChangedListener {
            Toast.makeText(this@AddActivity,"Name Changed", Toast.LENGTH_LONG).show()
        }

        et_quantity.addTextChangedListener {
            Toast.makeText(this@AddActivity,"Quantity Changed", Toast.LENGTH_LONG).show()
        }
    }
}