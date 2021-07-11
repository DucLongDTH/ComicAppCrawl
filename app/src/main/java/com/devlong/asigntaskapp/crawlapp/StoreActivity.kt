package com.devlong.asigntaskapp.crawlapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.databinding.ActivityStoreBinding

class StoreActivity :AppCompatActivity(){
    private lateinit var binding: ActivityStoreBinding
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store)
        binding.tab.setupWithViewPager(binding.vp)
        binding.vp.adapter = StoreAdapter(supportFragmentManager)
    }
}
