package com.example.assignmenttest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.assignmenttest.R
import com.example.assignmenttest.databinding.ItemNewsBinding
import com.example.assignmenttest.ui.interfaces.Callback
import com.example.assignmenttest.ui.model.MNews

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

    }
    class ViewHolder(internal var binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)
}