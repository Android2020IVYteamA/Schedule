package com.example.ischeduler.ui.Option

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.ischeduler.R
import com.example.ischeduler.Schedule_EditActivity
import com.example.ischeduler.gakka_editActivity
import kotlinx.android.synthetic.main.fragment_option.*
import kotlinx.android.synthetic.main.fragment_option.view.*

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

            root.opbutton.setOnClickListener {

                val intent = Intent(activity, gakka_editActivity::class.java)
                startActivity(intent)
        }
        return root
    }
}
