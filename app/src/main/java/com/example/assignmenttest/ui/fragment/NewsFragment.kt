package com.example.assignmenttest.ui.fragment

import com.example.assignmenttest.R
import com.example.assignmenttest.base.BaseFragment
import com.example.assignmenttest.databinding.FragmentNewsListBinding
import com.example.assignmenttest.ui.model.MNews
import com.example.assignmenttest.ui.viewmodel.MainViewModel
import java.util.*

class NewsFragment(private val newsList: ArrayList<MNews>?) :
    BaseFragment<FragmentNewsListBinding, MainViewModel>(){

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun layoutId(): Int = R.layout.fragment_news_list

    override fun fragmentInitialized() {

    }
}
