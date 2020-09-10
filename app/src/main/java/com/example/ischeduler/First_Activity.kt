package com.example.ischeduler

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_first_.*

class First_Activity : AppCompatActivity() {
    private lateinit var realm: Realm




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_)
        val dataStore: SharedPreferences = getSharedPreferences("DataStore", Context.MODE_PRIVATE)
        realm= Realm.getDefaultInstance()

        gakka.onItemSelectedListener=
            object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val spinner=parent as? Spinner
                    val item=spinner?.selectedItem as? String
                    val gakkaText=item
                    val editor = dataStore.edit()
                    editor.putString("gakka",gakkaText)

                    //editor.commit();
                    editor.apply()


                    item?.let{

                    }
                }
            }

        year.onItemSelectedListener=
            object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val spinner2=parent as? Spinner
                    val item2=spinner2?.selectedItem as? String
                    val yeartext=item2
                    val editor = dataStore.edit()
                    editor.putString("year",yeartext)

                    //editor.commit();
                    editor.apply()
                    item2?.let{

                    }
                }
            }


        addbutton.setOnClickListener {view: View ->
            realm.executeTransaction { db:Realm ->
                val maxId=db.where<gakka>().max("id")
                val nextId=(maxId?.toLong() ?: 0L)+1
                val schedule=db.createObject<gakka>(nextId)

                val gakkastr = dataStore.getString("gakka", "NoData")
                schedule.gakka=gakkastr

                val yearstr = dataStore.getString("year", "NoData")
                schedule.year=yearstr
                Log.d("LaunchChecker",schedule.gakka);
                Log.d("AppLaunchChecker",schedule.year);



            }
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }


}
