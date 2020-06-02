package com.example.assignmenttest.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.assignmenttest.R
import com.example.assignmenttest.databinding.FragmentNewsListBinding
import com.example.assignmenttest.ui.activity.WebViewActivity
import com.example.assignmenttest.ui.interfaces.Callback
import com.example.assignmenttest.ui.adapter.NewsAdapter
import com.example.assignmenttest.ui.model.MNews
import com.example.assignmenttest.util.Constant.URL_Link
import java.util.ArrayList

class NewsFragment(private val newsList: ArrayList<MNews>?) : Fragment(),
    Callback {
    private lateinit var binding: FragmentNewsListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_news_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       init()
    }

    private fun init() {
        binding.rvData.adapter= newsList?.let {
            NewsAdapter(
                it,
                this
            )
        }
    }

    override fun openSelectedItem(item: MNews) {
        startActivity(Intent(activity, WebViewActivity::class.java).putExtra(URL_Link,item.url))
    }
}
