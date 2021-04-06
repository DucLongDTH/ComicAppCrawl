package com.devlong.asigntaskapp.crawlapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.apptruyen.Home
import com.devlong.asigntaskapp.databinding.AppMainBinding
import com.google.android.material.navigation.NavigationView

class MainAppActivity : AppCompatActivity(),View.OnClickListener,NavigationView.OnNavigationItemSelectedListener,HomeFragment.IHomeFragment {
    private lateinit var binding: AppMainBinding
    private lateinit var toolbar: Toolbar
    private lateinit var drawler: DrawerLayout
    private lateinit var navigationView: NavigationView
    private val homepage = HomeFragment(this)
    private lateinit var avt:ImageView
    private val  truyenCuoi = TruyenCoTichTopic("https://truyencotich.vn/truyen-cuoi")
    private val truyenCoTich = TruyenCoTichTopic("https://truyencotich.vn/truyen-co-tich/co-tich-viet-nam")
    private val truyenCoTichTG = TruyenCoTichTopic("https://truyencotich.vn/truyen-co-tich/co-tich-the-gioi")
    private val truyenDanGian = TruyenCoTichTopic("https://truyencotich.vn/truyen-dan-gian")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.app_main)
        navigationView = binding.navView
        drawler = binding.drawLayout
        toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this,drawler,toolbar,0,0)
        drawler.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment,HomeFragment(this@MainAppActivity))
            commit()
        }
        val headerView = navigationView.getHeaderView(0)
        avt = headerView.findViewById<ImageView>(R.id.avt)
        avt.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                intent = Intent(Intent.ACTION_PICK);
                intent.type = "image/*";
                startActivityForResult(intent, 1);
            }


        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            avt.setImageURI(data?.data)
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        changeFragment(item.itemId)
        drawler.closeDrawer(GravityCompat.START)
        return true
    }

    private fun changeFragment(itemId: Int){
        when(itemId){
            R.id.cv_cuoi,R.id.menu_itemTopic2 ->{
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_page_rtl,R.anim.exit_page_rtl,R.anim.enter_page_ltr,R.anim.exit_page_ltr)
                    .replace(R.id.fragment,truyenCuoi)
                    .addToBackStack(null)
                    .commit()

            }
            R.id.menu_itemTopic1,R.id.cv_cotich ->{
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_page_rtl,R.anim.exit_page_rtl,R.anim.enter_page_ltr,R.anim.exit_page_ltr)
                    .replace(R.id.fragment,truyenCoTich)
                    .addToBackStack(null)
                    .commit()
            }
            R.id.menu_itemHome ->{
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_page_rtl,R.anim.exit_page_rtl,R.anim.enter_page_ltr,R.anim.exit_page_ltr)
                    .replace(R.id.fragment,homepage)
                    .commit()

            }
            R.id.cv_dangian,R.id.menu_itemTopic3 ->{
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_page_rtl,R.anim.exit_page_rtl,R.anim.enter_page_ltr,R.anim.exit_page_ltr)
                    .replace(R.id.fragment,truyenDanGian)
                    .addToBackStack(null)
                    .commit()

            }
            R.id.cv_cotichTG,R.id.menu_itemTopic4 ->{
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.enter_page_rtl,R.anim.exit_page_rtl,R.anim.enter_page_ltr,R.anim.exit_page_ltr)
                    .replace(R.id.fragment,truyenCoTichTG)
                    .addToBackStack(null)
                    .commit()

            }
        }
    }

    override fun openTopic(itemId: Int) {
        changeFragment(itemId)
    }

    override fun onClick(v: View?) {

    }
}