package com.devlong.asigntaskapp.crawlapp

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.devlong.asigntaskapp.MyApp
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.databinding.FragmentTruyenBinding
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.lang.Exception

class ListStoreFragment:Fragment(),CoTichAdapter.ICoTichAdapter {
    private lateinit var binding: FragmentTruyenBinding
    private val listTruyen = mutableListOf<TruyenData>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTruyenBinding.inflate(inflater,container,false)
        binding!!.rc.layoutManager = GridLayoutManager(context, 2)
        binding!!.rc.adapter = CoTichAdapter(this)
        loadDataFromPage(arguments!!.getString("URL")!!)
        return  binding.root
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
                Handler().postDelayed({
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
    override fun getCount() = listTruyen.size

    override fun getData(position: Int) = listTruyen[position]

    override fun onItemClick(position: Int) {
        (activity!!.applicationContext as MyApp).item = listTruyen[position]
        val intent = Intent(binding.root.context, ContentActivity::class.java)
        startActivity(intent)
        activity!!.overridePendingTransition(R.anim.enter_page_rtl, R.anim.exit_page_rtl)
    }

}