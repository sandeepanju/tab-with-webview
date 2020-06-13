package com.example.assignmenttest.util


import androidx.lifecycle.MutableLiveData
import com.example.assignmenttest.ui.model.GeneralModel
import com.example.assignmenttest.ui.model.MNews
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
//     @GET("top-headlines")
//   suspend fun getNewsList(@Query("country") country: String="us",
//                           @Query("category") category: String="business",
//                           @Query("apiKey") apiKey: String): Response<GeneralModel>
}
