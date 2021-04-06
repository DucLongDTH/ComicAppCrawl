package com.devlong.asigntaskapp.crawlapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devlong.asigntaskapp.databinding.TruyenItemBinding

/*
* Author by Admin on 28/03/21
*/

class CoTichAdapter : RecyclerView.Adapter<CoTichAdapter.CoTichHolder>{
    private lateinit var  inter : ICoTichAdapter
    constructor( inter : ICoTichAdapter){
        this.inter = inter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoTichHolder {
        return CoTichHolder(TruyenItemBinding.inflate(LayoutInflater.from(parent.context),parent,false ), inter)
    }

    override fun getItemCount(): Int {
        return inter.getCount()
    }

    override fun onBindViewHolder(holder: CoTichHolder, position: Int) {
        holder.binding.data = inter.getData(position)
    }

    interface ICoTichAdapter{
        fun getCount(): Int
        fun getData(position: Int): TruyenData
        fun onItemClick(position: Int)
    }


    class CoTichHolder(val binding: TruyenItemBinding,inter:ICoTichAdapter):RecyclerView.ViewHolder(binding.root){
        init {
            binding.root.setOnClickListener{
                inter.onItemClick(adapterPosition)
            }
        }
    }


}