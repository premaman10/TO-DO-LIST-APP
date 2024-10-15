package com.example.recyclerviewone

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewone.app_interface.CellClickListener

class UserAdapter(private val userList: ArrayList<UserMd>, private val context: Context, private val cellClickListener: CellClickListener): RecyclerView.Adapter <UserAdapter.MyViewHolder>(){

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val task: TextView = itemView.findViewById(R.id.txtTaskName)
        val description: TextView = itemView.findViewById(R.id.txtDescription)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)

        fun bindView(std: UserMd) {
            task.text = std.task
            description.text = std.description
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.bindView(currentItem)
        holder.itemView.setOnClickListener {

            cellClickListener.onCellClickListener(currentItem, position)

        }
        holder.btnDelete.setOnClickListener {
            userList.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}