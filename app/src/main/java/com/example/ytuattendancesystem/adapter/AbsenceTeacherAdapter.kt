package com.example.ytuattendancesystem.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.ytuattendancesystem.R

class AbsenceTeacherAdapter(
    context: Context?,
    val studentsNumberr: MutableList<String>,
    val studentsNamee: MutableList<String>,
    val absenteeismList: MutableList<Boolean>

) : BaseAdapter() {

    var inflator = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        if (convertView == null) {
            view = this.inflator.inflate(R.layout.absente_teacher_list, parent, false)
        } else {
            view = convertView
        }
        val studentsName = view!!.findViewById(R.id.studentNameTeacher) as TextView
        val studentsNumber = view.findViewById(R.id.studentNumber) as TextView
        studentsName.text = studentsNamee.get(position)
        studentsNumber.text = studentsNumberr.get(position)
        return view
    }

    override fun getItem(position: Int): Any {
       return studentsNamee[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
       return studentsNamee.size
    }
}