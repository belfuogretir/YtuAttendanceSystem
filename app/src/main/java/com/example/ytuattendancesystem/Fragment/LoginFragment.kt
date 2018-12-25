package com.example.ytuattendancesystem.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.example.ytuattendancesystem.R
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentTransaction = fragmentManager!!.beginTransaction()

        button_login.setOnClickListener {
            val email = et_username.text.toString()
//            val password = et_password.text.toString()

            when (email) {
                "s" ->  fragmentTransaction.replace(R.id.frame_layout,StudentMainFragment()).commit()
                "t" ->  fragmentTransaction.replace(R.id.frame_layout,TeacherMainFragment()).commit()
                else -> {
                    Toast.makeText(activity, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    companion object {
        fun newInstance() = LoginFragment()
    }
}
