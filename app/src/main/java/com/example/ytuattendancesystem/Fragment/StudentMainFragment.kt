package com.example.ytuattendancesystem.Fragment


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.ytuattendancesystem.R
import kotlinx.android.synthetic.main.fragment_student_main.*
import java.text.SimpleDateFormat
import java.util.*

class StudentMainFragment : Fragment() {

    val currentDate = SimpleDateFormat("EE").format(Date())
    val currentTime = SimpleDateFormat("HH").format(Date())
    val dateNow = currentDate + currentTime


    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentTransaction = fragmentManager!!.beginTransaction()


        when(currentDate){
            "Pa" -> Toast.makeText(activity, "Authentication Failed.", Toast.LENGTH_SHORT).show()
            "Sa" -> Toast.makeText(activity, "Authentication Failed.", Toast.LENGTH_SHORT).show()
            "Çar" ->{
                c09.setBackgroundColor(R.color.colorAccent)
                c09.setOnClickListener {
                    fragmentTransaction.replace(R.id.frame_layout,ConnectionFragment()).commit()
                }
            }
            "Pe" -> Toast.makeText(activity, "Authentication Failed.", Toast.LENGTH_SHORT).show()
            "Cu" -> Toast.makeText(activity, "Authentication Failed.", Toast.LENGTH_SHORT).show()
         }



        iv_student_profile.setOnClickListener {
            fragmentTransaction.replace(R.id.frame_layout,StudentProfileFragment()).commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_main, container, false)
    }

}


