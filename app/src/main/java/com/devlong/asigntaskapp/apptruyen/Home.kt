package com.devlong.asigntaskapp.apptruyen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.devlong.asigntaskapp.R
import com.devlong.asigntaskapp.databinding.FragmentHomeBinding

class Home : Fragment(R.layout.fragment_home), View.OnClickListener {
    lateinit var binding:FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        binding.btnHome.setOnClickListener(this)

        var x = if((activity!!.applicationContext as MyContext).name != null) (activity!!.applicationContext as MyContext).name else "Demo"
        binding.textDemo.text = x
        return binding.root
    }

    override fun onClick(v: View?) {
        Toast.makeText(binding.root.context,"haha",Toast.LENGTH_LONG).show()
    }

}