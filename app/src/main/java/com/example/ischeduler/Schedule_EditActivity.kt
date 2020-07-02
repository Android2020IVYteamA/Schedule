package com.example.ischeduler

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import kotlinx.android.synthetic.main.activity_schedule__edit.*
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
    }
}
