package com.leo123nunes.guestapp.ui.all

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.leo123nunes.guestapp.R

class AllFragment : Fragment() {

    private lateinit var allViewModel: AllViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        allViewModel =
                ViewModelProviders.of(this).get(AllViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_all, container, false)
        val textView: TextView = root.findViewById(R.id.text_all)
        allViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}