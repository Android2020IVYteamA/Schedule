package com.example.ischeduler.ui.Option

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ischeduler.R

class OptionFragment : Fragment() {

    private lateinit var slideshowViewModel: OptionViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
                ViewModelProviders.of(this).get(OptionViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_option, container, false)

        slideshowViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }
}
