package com.devlong.asigntaskapp.crawlapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.devlong.asigntaskapp.MyApp
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.databinding.ActivityContentBinding
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class ContentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_content)
        val link = (applicationContext as MyApp).item!!.linkHtml
        loadDataFromPage(link)

//        setSupportActionBar(binding.toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    private fun loadDataFromPage(link: String) {
        val taskLoader = object: AsyncTask<String, Void, TruyenData>(){
            override fun doInBackground(vararg params: String): TruyenData {
                return loadDataContent(params[0])
            }

            override fun onPostExecute(result: TruyenData) {
                binding.data = result
            }
        }
        taskLoader.execute(link)
    }

    private fun loadDataContent(link: String): TruyenData {
        var texts =""
        val doc = Jsoup.connect(link).get()
        val els: Elements = doc.select("div.site-content")
        val title = els.get(0).select("article").select("header").text()
        val img = els.get(0).select("article").select("center").select("img").attr("src")
        val size_text = els.get(0).select("article").select("div.entry-content").select("p").size
        for (element in els.get(0).select("article").select("div.entry-content").select("p")){
            texts +=""+ element.text()+"\n \n"
        }
        val truyenData = TruyenData(title,texts,"",img)
        texts.split("")
        return truyenData
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.enter_page_ltr,R.anim.exit_page_ltr)
    }
}