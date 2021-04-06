package com.devlong.asigntaskapp

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.databinding.DataBindingUtil
import com.devlong.asigntaskapp.databinding.ActivityMainBinding
import org.jsoup.Jsoup
import java.net.URL
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.btnStart.setOnClickListener(this)
        loadDataAsync("https://truyencotich.vn/truyen-cuoi")
    }

    override fun onClick(v: View?) {
        initAsyn()

    }

    private fun initAsyn() {
        // vararg params: 1 array
        //  param1 truyen vao doIn
        // param 2 : kieu du lieu de trao doi giua thread khac den main thread
        // param 3 : return
        // 1 thread duy nhat duoc tao
        val asyn = @SuppressLint("StaticFieldLeak")
        object :AsyncTask<Int,String,String>() {
            override fun doInBackground(vararg params: Int?): String {
                for(i in 0..100){
                    publishProgress(i.toString())
                    SystemClock.sleep(1000)
                }
                return "Done !!!"
            }
            /*Cap nhat giao dien cho Main thread*/
            override fun onProgressUpdate(vararg values: String) {
                binding.tvCount.setText(values[0])
            }

            override fun onPostExecute(result: String?) {
                binding.tvCount.setText(result)
            }

        }
        asyn.executeOnExecutor(Executors.newFixedThreadPool(1))
    }
    private fun loadDataAsync(link:String){
        val asyn = @SuppressLint("StaticFieldLeak")
        object :AsyncTask<String,Void,String>(){
            override fun doInBackground(vararg params: String?): String {
                loadDataTC(params[0]!!)
                return ""
            }
        }
        asyn.execute(link)
    }
    private fun loadDataTC(link:String){
       val doc =  Jsoup.connect(link).get()
    }
}

