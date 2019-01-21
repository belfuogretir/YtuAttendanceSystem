package com.example.ytuattendancesystem.fragment


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import com.example.ytuattendancesystem.R
import com.jakewharton.threetenabp.AndroidThreeTen
import de.tobiasschuerg.weekview.data.Event
import de.tobiasschuerg.weekview.data.EventConfig
import de.tobiasschuerg.weekview.data.WeekData
import kotlinx.android.synthetic.main.fragment_student_main.*
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import java.util.*
import kotlin.math.absoluteValue



class StudentMainFragment : Fragment() {


    private val random = Random()


    private val data: WeekData by lazy {
        WeekData().apply {
            (2..2).map {
                this.add(createSampleEntry(it, LocalTime.of(13, 0),
                    LocalTime.of(13, 0).plusMinutes(180),"Mekanik"))
                this.add(createSampleEntry(3,LocalTime.of(9, 0),
                    LocalTime.of(9, 0).plusMinutes(180),"Yöneylem"))
                this.add(createSampleEntry(4,LocalTime.of(14, 0),
                    LocalTime.of(14, 0).plusMinutes(180),"Modelleme"))
                this.add(createSampleEntry(5,LocalTime.of(9, 0),
                    LocalTime.of(9, 0).plusMinutes(180),"Mobil Programlama"))
                this.add(createSampleEntry(5,LocalTime.of(13, 0),
                    LocalTime.of(13, 0).plusMinutes(180),"Nesne Tabanlı Programlama"))
            }
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AndroidThreeTen.init(activity)
        super.onViewCreated(view, savedInstanceState)
        val fragmentTransaction = fragmentManager!!.beginTransaction()

        val config = EventConfig(showSubtitle = false, showTimeEnd = false)
        week_view_foo.eventConfig = config

        week_view_foo.addLessonsToTimetable(data)
        week_view_foo.setLessonClickListener {
            if(it.event.title.equals("Yöneylem")){
                fragmentTransaction.addToBackStack(null)
                fragmentManager!!.popBackStack()
                fragmentTransaction.replace(R.id.frame_layout,ConnectionFragment()).commit()
                }else{
                val fragment = AbsenceScreenFragment()
                val args = Bundle()
                args.putString("lessonName",it.event.title)
                fragment.arguments = args
                fragmentTransaction.addToBackStack(null)
                fragmentManager!!.popBackStack()
                fragmentTransaction.replace(R.id.frame_layout,fragment).commit()
            }
        }
        registerForContextMenu(week_view_foo)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_student_main, container, false)
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


