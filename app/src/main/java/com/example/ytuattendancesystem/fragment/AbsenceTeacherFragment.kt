package com.example.ytuattendancesystem.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ytuattendancesystem.R
import com.example.ytuattendancesystem.adapter.AbsenceTeacherAdapter
import com.example.ytuattendancesystem.models.AbsenteeismResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_absence_teacher.*
import java.io.IOException

class AbsenceTeacherFragment : Fragment(){



    var studentsName: MutableList<String> = mutableListOf()
    var studentsNumber: MutableList<String> = mutableListOf()
    val absenteeismList :MutableList<Boolean> = mutableListOf()

    var titleTeacher = ""
    val gson = Gson()

    fun json(): String? {
        val jsonString: String?
        try {
            val files = activity!!.assets.open("absenteeismList.json")
            val size = files.available()
            val buffer = ByteArray(size)
            files.read(buffer)
            files.close()
            jsonString = String(buffer, Charsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return jsonString
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val absenteeismResponse = gson.fromJson(json(), AbsenteeismResponse::class.java)
        for (i in absenteeismResponse.absenteeims!!.indices){
            if(absenteeismResponse.absenteeims[i].lessonName.toString().equals(titleTeacher)){
                for(j in absenteeismResponse.absenteeims[i].students!!.indices){
                    studentsName.add(absenteeismResponse.absenteeims[i].students!![j].studentsName.toString())
                    studentsNumber.add(absenteeismResponse.absenteeims[i].students!![j].StudentsNum.toString())
//                    for(k in absenteeismResponse.absenteeims[i].students!![i].weeks!!.indices){
//                        absenteeismList.add(absenteeismResponse.absenteeims[i].students!![j].weeks!![k].status!!)
//                    }
                }
            }
        }
        return inflater.inflate(R.layout.fragment_absence_teacher, container, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString("lessonNameAbsence")?.let {
            titleTeacher = it
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lessonNameTakeAttandance.text = titleTeacher

        absenceteacherlistview.adapter = AbsenceTeacherAdapter(this.context,studentsNumber,studentsName,absenteeismList)
    }


}
