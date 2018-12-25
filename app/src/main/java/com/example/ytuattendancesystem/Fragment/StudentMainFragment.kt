package com.example.ytuattendancesystem.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ytuattendancesystem.R
import kotlinx.android.synthetic.main.fragment_student_main.*

class StudentMainFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentTransaction = fragmentManager!!.beginTransaction()

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
