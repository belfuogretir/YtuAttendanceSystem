package com.example.ytuattendancesystem.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ytuattendancesystem.Fragment.LoginFragment
import com.example.ytuattendancesystem.R


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.frame_layout, LoginFragment.newInstance()).commit()
    }
}
