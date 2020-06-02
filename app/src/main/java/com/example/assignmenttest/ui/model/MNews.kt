package com.example.assignmenttest.ui.model

data class MNews(val source : MSource,
                 val auther:String,
                 val title:String,
                 val description:String,
                 val url:String,
                 val urlToImage:String,
                 val publishedAt:String,
                 val content:String)

data class MSource(val id:String,val name:String)
