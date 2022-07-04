package com.example.retrofitdemo.adopters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.R
import com.google.android.material.textview.MaterialTextView

class ApiAdopter(private var mapList : ArrayList<Map<String,Double>>):RecyclerView.Adapter<ApiAdopter.MyViewHolder>() {
    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var txt1: MaterialTextView = itemView.findViewById(R.id.txtView1ApiDemo)
        var txt2: MaterialTextView = itemView.findViewById(R.id.textView2ApiDemo)
        var txt3:MaterialTextView = itemView.findViewById(R.id.txtView3ApiDemo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apidemo_recyclerview_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPosition = mapList[position]

        holder.apply {
            txt1.text = currentPosition[currentPosition.keys.toList()[0]].toString()
            txt2.text = currentPosition[currentPosition.keys.toList()[1]].toString()
            txt3.text = currentPosition[currentPosition.keys.toList()[2]].toString()
        }
    }

    override fun getItemCount(): Int {
        return mapList.size
    }
}