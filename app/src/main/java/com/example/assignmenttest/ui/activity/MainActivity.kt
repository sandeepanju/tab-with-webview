package com.example.assignmenttest.ui.activity

import com.example.assignmenttest.R
import com.example.assignmenttest.base.BaseActivity
import com.example.assignmenttest.databinding.ActivityMainBinding
import com.example.assignmenttest.ui.viewmodel.MainViewModel

class MainActivity :  BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun layoutId(): Int = R.layout.activity_main
    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java
}
