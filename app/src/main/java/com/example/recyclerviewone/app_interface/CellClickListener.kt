package com.example.recyclerviewone.app_interface

import com.example.recyclerviewone.UserMd

interface CellClickListener {
    fun onCellClickListener(data: UserMd, position: Int)
}