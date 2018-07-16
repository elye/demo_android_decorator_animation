package com.elyeproj.animatedecorator

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.view_item.view.txt_data

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    fun bind(data: String) {
        view.txt_data.text = data
    }
}
