package com.example.ischeduler

import android.content.Intent
import android.os.Bundle
import android.text.format.DateFormat
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ischeduler.EventFragment.Companion.newInstance
import com.example.ischeduler.timetableFragment.Companion.newInstance
import com.example.ischeduler.ui.Month.MonthFragment
import kotlinx.android.synthetic.main.activity_schedule__edit.*
import java.time.Month
import java.util.*

class Schedule_EditActivity : AppCompatActivity()
,DatePickerFragment.OnDateSelectedListener
,TimePickerFragment.OnTimeSelectedListener{

    override fun onSelected(year: Int, month: Int, date: Int) {
        val c= Calendar.getInstance()
        c.set(year, month, date)
        datetext.text= DateFormat.format("yyyy/MM/dd",c)
    }

    override fun onSelected(hourOfDay: Int, minute: Int) {
        timetext.text="%1$02d:%2$02d".format(hourOfDay,minute)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule__edit)

        datetext.setOnClickListener {
            val dialog=DatePickerFragment()
            dialog.show(supportFragmentManager,"date_dialog")
        }

        timetext.setOnClickListener {
            val dialog=TimePickerFragment()
            dialog.show(supportFragmentManager,"time_dialog")
        }
        backbutton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}
