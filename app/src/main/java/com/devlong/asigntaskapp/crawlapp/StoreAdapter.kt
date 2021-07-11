package com.devlong.asigntaskapp.crawlapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class StoreAdapter: FragmentPagerAdapter {
    constructor(fm: FragmentManager) : super(fm)

    override fun getItem(position: Int): Fragment {
        val fr = ListStoreFragment()
        val bundle = Bundle()
        when(position){
            0 -> bundle.putString("URL","https://truyencotich.vn/truyen-co-tich/co-tich-viet-nam")
            1 -> bundle.putString("URL","https://truyencotich.vn/truyen-co-tich/co-tich-the-gioi")
            2 -> bundle.putString("URL","https://truyencotich.vn/truyen-dan-gian")
            else -> bundle.putString("URL","https://truyencotich.vn/truyen-dan-gian")
        }
        fr.arguments = bundle
        return  fr
    }

    override fun getCount() = 4
    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Cổ tích VN"
            1 -> return "Cổ tích TG"
            2 -> return "Dân gian"
            else -> return "Truyện cười"
        }
    }
}