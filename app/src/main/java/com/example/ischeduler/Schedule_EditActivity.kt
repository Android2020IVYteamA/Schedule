package com.example.ischeduler

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.format.DateFormat
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_schedule__edit.*
import java.util.*

class Schedule_EditActivity : AppCompatActivity()
,DatePickerFragment.OnDateSelectedListener
,TimePickerFragment.OnTimeSelectedListener{
    private lateinit var realm: Realm


    override fun onSelected(year: Int, month: Int, date: Int) {
        val c= Calendar.getInstance()
        c.set(year, month, date)
        datetext.text= DateFormat.format("yyyy/M/d",c)
    }

    override fun onSelected(hourOfDay: Int, minute: Int) {
        timetext.text="%1$02d:%2$02d".format(hourOfDay,minute)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule__edit)
        realm=Realm.getDefaultInstance()

        val scheduleId=intent?.getLongExtra("schedule_id",-1L)
        if (scheduleId!=-1L){
            val schedule=realm.where<ISchedule>()
                .equalTo("id",scheduleId).findFirst()
            titleText.setText(schedule?.title)
            detailText.setText(schedule?.detail)
            datetext.setText(schedule?.date)
            timetext.setText(schedule?.time)
            placeText.setText(schedule?.place)
            delete.visibility=View.VISIBLE



        }else{
            delete.visibility=View.INVISIBLE
        }

        addbutton.setOnClickListener {view: View ->
            when (scheduleId){
                -1L ->{


            realm.executeTransaction { db:Realm ->
                val maxId=db.where<ISchedule>().max("id")
                val nextId=(maxId?.toLong() ?:0L)+1
                val schedule=db.createObject<ISchedule>(nextId)
                schedule.title=titleText.text.toString()
                schedule.detail=detailText.text.toString()
                schedule.date=datetext.text.toString()
                schedule.time=timetext.text.toString()
                schedule.place=placeText.text.toString()
            }
            Snackbar.make(view,"追加しました",Snackbar.LENGTH_SHORT)
                .setAction("戻る") {finish()}
                .setActionTextColor(Color.YELLOW)
                .show()
                }
                else ->{
                    realm.executeTransaction { db:Realm ->
                        val schedule=db.where<ISchedule>()
                            .equalTo("id",scheduleId).findFirst()
                        schedule?.title=titleText.text.toString()
                        schedule?.detail=detailText.text.toString()
                        schedule?.date=datetext.text.toString()
                        schedule?.time=timetext.text.toString()
                        schedule?.place=placeText.text.toString()
                    }
                    Snackbar.make(view,"修正しました",Snackbar.LENGTH_SHORT)
                        .setAction("戻る"){finish()}
                        .setActionTextColor(Color.YELLOW)
                        .show()
                }
            }


        }

        datetext.setOnClickListener {
            val dialog=DatePickerFragment()
            dialog.show(supportFragmentManager,"date_dialog")
        }

        timetext.setOnClickListener {
            val dialog=TimePickerFragment()
            dialog.show(supportFragmentManager,"time_dialog")
        }
        delete.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        delete.setOnClickListener { view: View ->
            realm.executeTransaction { db:Realm ->
                db.where<ISchedule>().equalTo("id",scheduleId)
                    ?.findFirst()
                    ?.deleteFromRealm()
            }
            Snackbar.make(view,"削除しました",Snackbar.LENGTH_SHORT)
                .setAction("戻る"){finish()}
                .setActionTextColor(Color.YELLOW)
                .show()

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }

}
