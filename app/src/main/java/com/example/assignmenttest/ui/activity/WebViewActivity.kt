package com.example.assignmenttest.ui.activity

import android.os.Bundle
import android.webkit.WebViewClient
import com.example.assignmenttest.R
import com.example.assignmenttest.base.BaseActivity
import com.example.assignmenttest.databinding.WebviewLayoutBinding
import com.example.assignmenttest.ui.viewmodel.MainViewModel
import com.example.assignmenttest.util.Constant


class WebViewActivity : BaseActivity<WebviewLayoutBinding, MainViewModel>(){
    private val urlLink by lazy { intent.getStringExtra(Constant.URL_Link) }
    override fun layoutId(): Int = R.layout.webview_layout
    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()
    }

    private fun initialize() {
        binding.webV.webViewClient = WebViewClient()
        binding.webV.settings.builtInZoomControls = true;
        binding.webV.loadUrl(urlLink)
    }
}