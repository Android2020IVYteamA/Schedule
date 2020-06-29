package com.example.ischeduler.ui.Month

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ischeduler.R
import kotlinx.android.synthetic.main.fragment_month.*

class MonthFragment : Fragment() {

    private lateinit var galleryViewModel: MonthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
                ViewModelProviders.of(this).get(MonthViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_month, container, false)
        fab.setOnClickListener {view ->


        }


        galleryViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }


}
