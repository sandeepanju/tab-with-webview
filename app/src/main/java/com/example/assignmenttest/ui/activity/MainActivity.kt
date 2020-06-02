package com.example.assignmenttest.ui.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.assignmenttest.R
import com.example.assignmenttest.base.BaseActivity
import com.example.assignmenttest.databinding.ActivityMainBinding
import com.example.assignmenttest.ui.adapter.NewsViewPagerAdapter
import com.example.assignmenttest.ui.model.MNews
import com.example.assignmenttest.ui.model.ViewState
import com.example.assignmenttest.ui.viewmodel.MainViewModel
import com.example.assignmenttest.util.Constant.API_KEY

class MainActivity :  BaseActivity<ActivityMainBinding, MainViewModel>() {
    private val  viewPagerAdapter  by lazy {
        NewsViewPagerAdapter(
            supportFragmentManager,
            mapNews,
            titleList
        )
    }
    private var mapNews = HashMap<String, ArrayList<MNews>>()
    private var titleList = ArrayList<String>()

    override fun layoutId(): Int = R.layout.activity_main
    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
        obServeListData()
    }

    private fun fetchNewsData() {
        viewModel.getImageList(API_KEY)
    }

    private fun initialize() {
        binding.keyTabs.setupWithViewPager(binding.viewpager)
        binding.viewpager.adapter=viewPagerAdapter
        fetchNewsData()
    }

    private fun obServeListData() {
        viewModel.imageResponse.observe(this, Observer {
            when(it){
                is  ViewState.Success->{
                    binding.progressBar.visibility= View.GONE
                    updateNewsList(it.data as ArrayList<MNews>)
                }
                is  ViewState.Loading->binding.progressBar.visibility= View.VISIBLE
                is  ViewState.Error->binding.progressBar.visibility= View.GONE
                is  ViewState.NetworkError->binding.progressBar.visibility= View.GONE
            }
        })
    }

    private fun updateNewsList(data: ArrayList<MNews>) {
        for (item in data) {
            if (mapNews.containsKey(item.source.name)){
                mapNews.getValue(item.source.name).add(item)
            }
            else{
                val sublist = ArrayList<MNews>()
                sublist.add(item)
                mapNews[item.source.name] = sublist
            }
        }
        mapNews.keys.forEach {
            titleList.add(it)
        }
        updatePager()
    }

    private fun updatePager() {
        binding.viewpager.adapter?.notifyDataSetChanged()
    }
}
