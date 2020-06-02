package com.example.assignmenttest.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignmenttest.base.BaseViewModel
import com.example.assignmenttest.ui.model.MNews
import com.example.assignmenttest.ui.model.ViewState
import com.example.assignmenttest.util.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext
import retrofit2.HttpException

class MainViewModel(context: Context): BaseViewModel(),KodeinAware{
    override val kodein by closestKodein(context)
    override val kodeinContext = kcontext(context)
    private val apiService by instance<ApiService>()
    val imageResponse: MutableLiveData<ViewState<List<MNews>>> = MutableLiveData()

   fun getImageList(apikey:String){
      viewModelScope.launch {
          val response =apiService.getNewsList(apiKey = apikey)
       withContext(Dispatchers.Main){
           try {
               if (response.isSuccessful) {
                   imageResponse.postValue(ViewState.Success(response.body()?.articles!!))
               } else {
                  imageResponse.postValue(ViewState.Error(response.errorBody().toString()))
               }
           } catch (e: HttpException) {
               imageResponse.postValue(ViewState.Error(e.message()))
           } catch (e: Throwable) {
               imageResponse.postValue(ViewState.Error(e.toString()))
           }
       }
      }
    }
}