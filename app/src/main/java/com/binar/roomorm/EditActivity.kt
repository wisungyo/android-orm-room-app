package com.binar.roomorm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_edit.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EditActivity : AppCompatActivity() {
    private lateinit var db: ItemDatabase
    private lateinit var item: Item

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        ItemDatabase.getInstance(this)?.let {
            db = it
        }
        intent.getParcelableExtra<Item>("item")?.let {
            item = it
        }

        et_name.setText(item.name)
        et_quantity.setText((item.quantity).toString())

        et_name.addTextChangedListener {
            Toast.makeText(this@EditActivity,"Name Changed", Toast.LENGTH_LONG).show()
        }

        et_quantity.addTextChangedListener {
            Toast.makeText(this@EditActivity,"Quantity Changed", Toast.LENGTH_LONG).show()
        }

        btn_add.setOnClickListener {
            item.apply {
                name = et_name.text.toString()
                quantity = et_quantity.text.toString().toInt()
            }

            GlobalScope.launch {
                val rowUpdated = db.itemDao().updateItem(item)
                runOnUiThread {
                    if (rowUpdated > 0) {
                        Toast.makeText(this@EditActivity, "Data updated" , Toast.LENGTH_LONG).show()
                        this@EditActivity.finish()
                    } else {
                        Toast.makeText(this@EditActivity, "Data not updated" , Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}