package com.binar.roomorm

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.stuff_item.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ItemAdapter(val listItem: List<Item>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private lateinit var db: ItemDatabase
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.stuff_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.tv_name.text = listItem[position].name
        holder.itemView.tv_quantity.text = listItem[position].quantity.toString()

        ItemDatabase.getInstance(holder.itemView.context)?.let {
            db = it
        }

        holder.itemView.iv_delete.setOnClickListener {
            GlobalScope.launch {
                val totalRowDeleted = db.itemDao().deleteItem(listItem[position])
                (holder.itemView.context as MainActivity).runOnUiThread {
                    if (totalRowDeleted > 0) {
                        Toast.makeText(holder.itemView.context, "Data ${listItem[position].name} deleted", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(holder.itemView.context, "Data ${listItem[position].name} fail to delete", Toast.LENGTH_LONG).show()
                    }
                }
                (holder.itemView.context as MainActivity).fetchData()
            }
        }

        holder.itemView.iv_update.setOnClickListener {
            val intentGoToEditActivity = Intent(it.context, EditActivity::class.java)
            intentGoToEditActivity.putExtra("item", listItem[position])
            it.context.startActivity(intentGoToEditActivity)
        }
    }
}