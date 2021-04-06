package com.devlong.asigntaskapp.apptruyen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.databinding.FragmentTopicBinding

/*
* Author by Admin on 26/03/21
*/

class TopicF : Fragment(), View.OnClickListener {
    lateinit var binding: FragmentTopicBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_topic,container,false)
        var view = binding.root

        return view
    }

    override fun onClick(v: View?) {

    }
}