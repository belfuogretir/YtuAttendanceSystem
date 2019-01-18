package com.example.ytuattendancesystem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ytuattendancesystem.R
import com.example.ytuattendancesystem.models.Students

class TakeAttandanceAdapter(
    context: Context?,
    val students: MutableList<String>
) : BaseAdapter() {

    var inflator = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        if (convertView == null) {
            view = this.inflator.inflate(R.layout.take_attendance_list, parent, false)
        } else {
            view = convertView
        }
        val list = view!!.findViewById(R.id.studentName) as TextView
        list.text = students.get(position)
        return view
    }

    override fun getItem(position: Int): Any {
       return students[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return students.size
    }

}