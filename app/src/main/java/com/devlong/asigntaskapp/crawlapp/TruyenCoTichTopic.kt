package com.devlong.asigntaskapp.crawlapp

import android.content.Intent
import android.opengl.Visibility
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.load.HttpException
import com.devlong.asigntaskapp.MyApp
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.databinding.TruyencotichTopicBinding
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.lang.Exception
import java.util.concurrent.Executors

/*
* Author by Admin on 28/03/21
*/

class TruyenCoTichTopic : Fragment, View.OnClickListener, CoTichAdapter.ICoTichAdapter {
    private lateinit var binding: TruyencotichTopicBinding
    private val listTruyen = mutableListOf<TruyenData>()
    private var currentPage = 1
    private lateinit var link: String

    //    private val exs = Executors.newFixedThreadPool(3)
    constructor(link: String) {
        this.link = link
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.truyencotich_topic, container, false)
        binding.rc.layoutManager = GridLayoutManager(binding.root.context, 2)
        binding.rc.adapter = CoTichAdapter(this)
        binding.rl.visibility = View.GONE
        if (listTruyen.size == 0) {
            loadDataFromPage(link)
        }
        binding.fab.setOnClickListener(this)
        return binding.root
    }

    private fun loadDataFromPage(link: String) {
        val taskLoad = object : AsyncTask<String, Void, MutableList<TruyenData>>() {
            override fun doInBackground(vararg params: String?): MutableList<TruyenData> {
                return loadDataTruyen(params[0]!!)
            }

            override fun onPostExecute(result: MutableList<TruyenData>) {
                if (result.size == 0){
                    Toast.makeText(binding.root.context, "Updating ..., sorry !!", Toast.LENGTH_LONG).show()
                    return
                }
                binding.rl.visibility = View.VISIBLE
                Handler().postDelayed({
                    binding.rl.visibility = View.GONE
                },3000)
                listTruyen.addAll(result)
                binding.rc.adapter!!.notifyDataSetChanged()
                binding.rc.smoothScrollToPosition(listTruyen.size - result.size)

            }

        }
        taskLoad.execute(link)
    }

    private fun loadDataTruyen(link: String): MutableList<TruyenData> {
        val truyenData = mutableListOf<TruyenData>()
        try {
            val doc = Jsoup.connect(link).get()
            val els: Elements = doc.select("div.clear")
            val mainEle = els.get(4)

            for (element in mainEle.select("article.entry-grid")) {
                val imageLink = element.selectFirst("img").attr("src")
                val title = element.selectFirst("h2.entry-title").text()
                val htmlLink = element.selectFirst("h2.entry-title")
                    .selectFirst("a").attr("href")
                val content = element.selectFirst("p.post-excerpt").text()
                truyenData.add(
                    TruyenData(title, content, htmlLink, imageLink)
                )
            }
            return truyenData
        } catch (e: Exception) {
            return truyenData
        }
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                currentPage++
                loadDataFromPage("$link/page/$currentPage")
            }
        }
    }

    override fun getCount(): Int {
        return listTruyen.size
    }

    override fun getData(position: Int): TruyenData {
        return listTruyen[position]
    }

    override fun onItemClick(position: Int) {
        (activity!!.applicationContext as MyApp).item = listTruyen[position]
        val intent = Intent(binding.root.context, ContentActivity::class.java)
        startActivity(intent)
        activity!!.overridePendingTransition(R.anim.enter_page_rtl,R.anim.exit_page_rtl)
//        Toast.makeText(binding.root.context, listTruyen[position].title,
//            Toast.LENGTH_SHORT).show()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

}