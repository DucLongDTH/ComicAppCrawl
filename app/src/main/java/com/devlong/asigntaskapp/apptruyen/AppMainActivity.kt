package com.devlong.asigntaskapp.apptruyen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.databinding.ActivityMainAppBinding
import com.devlong.asigntaskapp.databinding.AppMainBinding

class AppMainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainAppBinding
    lateinit var toogle: ActionBarDrawerToggle
    val homeF = Home()
    val topicF = TopicF()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_app)
        toogle = ActionBarDrawerToggle(this,binding.drawLayout,R.string.open,R.string.close)
        binding.drawLayout.addDrawerListener(toogle)
        toogle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_itemHome->{
                    (applicationContext as MyContext).name = "Home"
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment,homeF)
                        addToBackStack(null);
                        commit()
                    }

                }
                R.id.menu_itemTopic1->{
                    (applicationContext as MyContext).name = "Topic 1"
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.fragment,topicF)
                        commit()
                    }
                }
                R.id.menu_itemTopic2->{
                    (applicationContext as MyContext).name = "Topic 2"
                    supportFragmentManager.beginTransaction().apply {

                        remove(homeF)
                        replace(R.id.fragment,homeF)
                        addToBackStack(null);
                        commit()
                    }
                }
            }
            binding.drawLayout.closeDrawer(GravityCompat.START)
            false
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}