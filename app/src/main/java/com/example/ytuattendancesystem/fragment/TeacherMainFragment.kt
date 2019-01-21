package com.example.ytuattendancesystem.fragment


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.example.ytuattendancesystem.Fragment.AbsenceTeacherFragment

import com.example.ytuattendancesystem.R
import com.jakewharton.threetenabp.AndroidThreeTen
import de.tobiasschuerg.weekview.data.Event
import de.tobiasschuerg.weekview.data.EventConfig
import de.tobiasschuerg.weekview.data.WeekData
import kotlinx.android.synthetic.main.fragment_teacher_main.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.*
import kotlin.math.absoluteValue

class TeacherMainFragment : Fragment(){

    private val random = Random()


    private val data: WeekData by lazy {
        WeekData().apply {
            (2..2).map {
                this.add(createSampleEntry(it, LocalTime.of(11, 0),
                    LocalTime.of(11, 0).plusMinutes(120),"Olasılık"))
                this.add(createSampleEntry(3,LocalTime.of(9, 0),
                    LocalTime.of(9, 0).plusMinutes(180),"Yöneylem"))
                this.add(createSampleEntry(3,LocalTime.of(14, 0),
                    LocalTime.of(14, 0).plusMinutes(180),"Finans Matematiği"))
                this.add(createSampleEntry(5,LocalTime.of(9, 0),
                    LocalTime.of(9, 0).plusMinutes(180),"Lineer Programlam Teorisi"))
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidThreeTen.init(activity)
        super.onViewCreated(view, savedInstanceState)
        val fragmentTransaction = fragmentManager!!.beginTransaction()

        val config = EventConfig(showSubtitle = false, showTimeEnd = false)
        week_view_t.eventConfig = config

        week_view_t.addLessonsToTimetable(data)
        week_view_t.setLessonClickListener {
            if(it.event.title.equals("Yöneylem")){
                val fragment = TakeAttendanceFragment()
                val args = Bundle()
                args.putString("lessonNameTeacher",it.event.title)
                fragment.arguments = args
                fragmentTransaction.addToBackStack(null)
                fragmentManager!!.popBackStack()
                fragmentTransaction.replace(R.id.frame_layout,fragment).commit()
            }else{
                val fragment2 = AbsenceTeacherFragment()
                val args = Bundle()
                args.putString("lessonNameAbsence",it.event.title)
                fragment2.arguments = args
                fragmentTransaction.addToBackStack(null)
                fragmentManager!!.popBackStack()
                fragmentTransaction.replace(R.id.frame_layout,fragment2).commit()
            }
        }
        registerForContextMenu(week_view_t)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_teacher_main, container, false)
    }

    private fun createSampleEntry(day: Int, startTime: LocalTime, endTime: LocalTime, name: String): Event.Single {
        return Event.Single(
            random.nextLong().absoluteValue,
            LocalDate.now(),
            name,
            name,
            "hey",
            day,
            startTime,
            endTime,
            null,
            null,
            Color.WHITE,
            randomColor()
        )
    }

    private fun randomColor(): Int {
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }


}
