package com.example.retrofitdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdopter(val context: Context,val article: List<Article>) : RecyclerView.Adapter<NewsAdopter.ArticleViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)

        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = article[position]
        holder.newsTitle.text = article.title
        holder.newsDescription.text = article.description
        //holder.newsImage.setImageResource()
        Glide.with(context).load(article.urlToImage).into(holder.newsImage)
    }

    override fun getItemCount(): Int {
        return article.size
    }


    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
        var newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)

    }

}