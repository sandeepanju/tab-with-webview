package com.example.assignmenttest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignmenttest.R
import com.example.assignmenttest.databinding.ItemNewsBinding
import com.example.assignmenttest.ui.interfaces.Callback
import com.example.assignmenttest.ui.model.MNews
import com.example.assignmenttest.util.Constant.REDABLE_DATE_FORMAT
import com.example.assignmenttest.util.Constant.SERVER_DATE_TIME_FORMAT
import com.example.assignmenttest.util.formatDateTime

class NewsAdapter(private val newsList: ArrayList<MNews>, private val callBack: Callback) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemNewsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_news, parent, false
        )
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val root = holder.binding
        val item = newsList[position]
               root.tvAuther.text=item.auther
               root.tvTitle.text=item.title
        Glide.with(root.root.context).load(item.urlToImage).error(R.drawable.ic_launcher_background).into(root.imgUrl)
               root.tvDateTime.text= formatDateTime(item.publishedAt,SERVER_DATE_TIME_FORMAT,REDABLE_DATE_FORMAT)
        root.root.setOnClickListener { callBack.openSelectedItem(item) }
    }
    class ViewHolder(internal var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
}