package com.example.ytuattendancesystem.Fragment


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.ytuattendancesystem.R
import com.jakewharton.threetenabp.AndroidThreeTen
import de.tobiasschuerg.weekview.data.Event
import de.tobiasschuerg.weekview.data.EventConfig
import de.tobiasschuerg.weekview.data.WeekData
import kotlinx.android.synthetic.main.fragment_student_main.*
import kotlinx.android.synthetic.main.fragment_teacher_main.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.*
import kotlin.math.absoluteValue

class TeacherMainFragment : Fragment() {

    private val random = Random()


    private val data: WeekData by lazy {
        WeekData().apply {
            var startTime: LocalTime
            (2..2).map {
                startTime = LocalTime.of(13, 0)
                val endTime = startTime.plusMinutes(180)
                this.add(createSampleEntry(it, startTime, endTime,"Mekanik"))
                val startTime2 = LocalTime.of(9, 0)
                val endTime2 = startTime2.plusMinutes(180)
                this.add(createSampleEntry(3,startTime2,endTime2,"Yöneylem"))
                val startTime3 = LocalTime.of(14, 0)
                val endTime3 = startTime3.plusMinutes(180)
                this.add(createSampleEntry(4,startTime3,endTime3,"Modelleme"))
                val startTime4 = LocalTime.of(9, 0)
                val endTime4 = startTime4.plusMinutes(180)
                this.add(createSampleEntry(5,startTime4,endTime4,"Mobil Programlama"))
                val startTime5 = LocalTime.of(13, 0)
                val endTime5 = startTime5.plusMinutes(180)
                this.add(createSampleEntry(6,startTime5,endTime5,"Nesneye Yönelik Programlama"))
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidThreeTen.init(activity)
        super.onViewCreated(view, savedInstanceState)

        val config = EventConfig(showSubtitle = false, showTimeEnd = false)
        week_view_t.eventConfig = config

        week_view_t.addLessonsToTimetable(data)
        week_view_t.setLessonClickListener {
            if(it.event.title.equals("Yöneylem")){

            }else{

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
