package com.example.ischeduler.ui.Year

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ischeduler.R

class YearFragment : Fragment() {

    private lateinit var homeViewModel: YearViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(YearViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_year, container, false)

        homeViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}
