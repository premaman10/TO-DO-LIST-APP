package com.example.recyclerviewone

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewone.app_interface.CellClickListener
import com.example.recyclerviewone.data_object.Call_Interface

class MainActivity : AppCompatActivity(), CellClickListener{
    lateinit var edtTask: EditText
    lateinit var edtDescription: EditText
    lateinit var btnSave: Button
    lateinit var btnSearch: Button
    lateinit var recyclerView: RecyclerView
    lateinit var task: String
    lateinit var description: String
    lateinit var toast: Toast
    lateinit var userArrayList: ArrayList<UserMd>

    var flag: Boolean = false
    var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtTask = findViewById(R.id.edtTask)
        edtDescription = findViewById(R.id.edtDescription)
        btnSave = findViewById(R.id.btnSave)
        btnSearch = findViewById(R.id.btnFetch)
        edtTask = findViewById(R.id.edtTask)
        edtDescription = findViewById(R.id.edtDescription)
        recyclerView = findViewById(R.id.recyclerUserList)
        userArrayList = arrayListOf<UserMd>()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        Call_Interface.interfaceRef = this

        btnSave.setOnClickListener {
            if (flag == false) {
                task = edtTask.text.toString()
                description = edtDescription.text.toString()
                edtTask.setText("")
                edtDescription.setText("")

                if (task.isEmpty() || description.isEmpty()) {
                    toast = Toast.makeText(applicationContext, "Blank not allowed", Toast.LENGTH_LONG)
                    toast.show()
                    return@setOnClickListener
                } else {
                    //val user = UserNewModel(username = username, phone = phone)
                    val user = UserMd(task, description)
                    userArrayList.add(user)
                    toast = Toast.makeText(applicationContext, "Task inserted successfully", Toast.LENGTH_LONG)
                    toast.show()
                    edtTask.requestFocus()
                }
                recyclerView.adapter = UserAdapter(userArrayList, this, this)
            } else {
                task = edtTask.text.toString()
                description = edtDescription.text.toString()
                edtTask.setText("")
                edtDescription.setText("")

                if (task.isEmpty() || description.isEmpty()) {
                    toast = Toast.makeText(applicationContext, "Update record", Toast.LENGTH_LONG)
                    toast.show()
                    return@setOnClickListener
                } else {
                    val user = UserMd(task, description)
                    userArrayList.set(position, user)
                }
                recyclerView.adapter = UserAdapter(userArrayList, this, this)
                btnSave.setText("Save")
                flag = false
            }

        }

        btnSearch.setOnClickListener {
            task = edtTask.text.toString()
            for (user in userArrayList) {
                if (user.task == task) {
                    user.task = description
                    recyclerView.adapter = UserAdapter(arrayListOf(user), this, this)
                }
            }
        }

        edtTask.doAfterTextChanged {
            if (edtTask.text.isBlank()) {
                recyclerView.adapter = UserAdapter(userArrayList, this, this)
            }
        }

    }

    override fun onCellClickListener(data: UserMd, position: Int) {
        edtTask.setText(data.task)
        edtDescription.setText(data.description)
        btnSave.setText("Update")
        flag = true
        this.position = position
        Toast.makeText(this,"Selected: ${data.task} and ${data.description}", Toast.LENGTH_SHORT).show()
    }
}