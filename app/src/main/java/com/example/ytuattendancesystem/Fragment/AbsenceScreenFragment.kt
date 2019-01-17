package com.example.ytuattendancesystem.Fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ytuattendancesystem.R
import kotlinx.android.synthetic.main.fragment_absence_screen.*




class AbsenceScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_absence_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        tv_title.text = arguments!!.getString("lessonName")
        if (arguments != null) {
            tv_title.text = arguments!!.getString("lessonName", "aa")
        }
    }
}
