package com.example.assignmenttest.ui.fragment

import android.content.Intent
import com.example.assignmenttest.R
import com.example.assignmenttest.base.BaseFragment
import com.example.assignmenttest.databinding.FragmentNewsListBinding
import com.example.assignmenttest.ui.activity.WebViewActivity
import com.example.assignmenttest.ui.adapter.NewsAdapter
import com.example.assignmenttest.ui.interfaces.Callback
import com.example.assignmenttest.ui.model.MNews
import com.example.assignmenttest.ui.viewmodel.MainViewModel
import com.example.assignmenttest.util.Constant.URL_Link
import java.util.*

class NewsFragment(private val newsList: ArrayList<MNews>?) :
    BaseFragment<FragmentNewsListBinding, MainViewModel>(),
    Callback {

    override fun openSelectedItem(item: MNews) {
        startActivity(Intent(activity, WebViewActivity::class.java).putExtra(URL_Link,item.url))
    }

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutId(): Int = R.layout.fragment_news_list

    override fun fragmentInitialized() {
        binding.rvData.adapter = newsList?.let {
            NewsAdapter(
                it,
                this
            )
        }
    }
}
