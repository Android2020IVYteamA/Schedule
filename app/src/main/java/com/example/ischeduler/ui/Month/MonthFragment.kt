package com.example.ischeduler.ui.Month

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ischeduler.ISchedule
import com.example.ischeduler.R
import com.example.ischeduler.ScheduleAdapter
import com.example.ischeduler.Schedule_EditActivity
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.fragment_month.*
import kotlinx.android.synthetic.main.fragment_month.view.*
import java.text.SimpleDateFormat
import java.time.Month
import java.time.Year
import java.util.*

class MonthFragment : Fragment() {

    private lateinit var galleryViewModel: MonthViewModel
    private lateinit var realm: Realm

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProviders.of(this).get(MonthViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_month, container, false)


        galleryViewModel.text.observe(viewLifecycleOwner, Observer {

        })
       root.calendarView.setOnDateChangeListener { view,  year, month, dayOfMonth ->


           val Month=month+1
           val Date = "$year/$Month/$dayOfMonth"




           realm = Realm.getDefaultInstance()
           list.layoutManager=LinearLayoutManager(context)
           val  schedule=realm.where<ISchedule>().equalTo("date",Date).findAll()
           val adapter=ScheduleAdapter(schedule)
           list.adapter=adapter

           adapter.setOnItemClickListener { id ->
               val intent = Intent(activity,Schedule_EditActivity::class.java)
                   .putExtra("schedule_id",id)
               startActivity(intent)
           }


       }



        root.fab.setOnClickListener {
            val intent = Intent(activity, Schedule_EditActivity::class.java)
            startActivity(intent)

        }
        return root
    }











}
