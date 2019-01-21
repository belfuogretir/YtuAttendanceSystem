package com.example.ytuattendancesystem.fragment


import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ytuattendancesystem.R
import com.example.ytuattendancesystem.models.AbsenteeismResponse
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_take_attendance.*
import java.io.IOException
import com.example.ytuattendancesystem.adapter.TakeAttandanceAdapter
import com.example.ytuattendancesystem.models.BluetoothDevices


class TakeAttendanceFragment : Fragment() {


    var titleTeacher =""
    val gson = Gson()

    var students: MutableList<String> = mutableListOf()
    val bluetoothDevice :MutableList<BluetoothDevices> = mutableListOf()
    lateinit var bl : BluetoothDevices


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
            if(absenteeismResponse.absenteeims[i].lessonName.equals(titleTeacher)){
                for(j in absenteeismResponse.absenteeims[i].students!!.indices){
                    students.add(absenteeismResponse.absenteeims[i].students!![j].studentsName.toString())
                }
            }
        }
        return inflater.inflate(R.layout.fragment_take_attendance, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        context!!.registerReceiver(mReceiver, filter)
        arguments?.getString("lessonNameTeacher")?.let {
            titleTeacher = it
        }
    }

    private val mReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action: String = intent.action
            when(action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    bl.deviceName = device.name
                    bl.deviceHardwareAddress = device.address
                    bluetoothDevice.add(bl)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lessonName.text = titleTeacher
        listview.adapter = TakeAttandanceAdapter(this.context,students)
        btnStart.setOnClickListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        context!!.unregisterReceiver(mReceiver)
    }

}
