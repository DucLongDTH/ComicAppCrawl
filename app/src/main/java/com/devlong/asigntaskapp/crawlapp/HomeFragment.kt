package com.devlong.asigntaskapp.crawlapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.databinding.HomeAppBinding

class HomeFragment : Fragment, View.OnClickListener {
    private lateinit var binding: HomeAppBinding
    private lateinit var inter : IHomeFragment
    constructor(inter : IHomeFragment){
        this.inter = inter
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_app, container, false)
        binding.cvCotich.setOnClickListener(this)
        binding.cvCuoi.setOnClickListener(this)
        binding.cvDangian.setOnClickListener(this)
        binding.cvCotichTG.setOnClickListener(this)
        return binding.root
    }

    interface IHomeFragment{
        fun openTopic(itemId: Int)
    }

    override fun onClick(v: View) {
        when(v.id){
            binding.cvCuoi.id ->{
                inter.openTopic(R.id.cv_cuoi)
            }
            binding.cvCotich.id ->{
                inter.openTopic(R.id.cv_cotich)
            }
            binding.cvCotichTG.id ->{
                inter.openTopic(R.id.cv_cotichTG)
            }
            binding.cvDangian.id ->{
                inter.openTopic(R.id.cv_dangian)
            }
        }
    }
}
