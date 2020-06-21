package com.example.assignmenttest.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beust.klaxon.*
import com.example.assignmenttest.base.BaseViewModel
import com.example.assignmenttest.ui.model.ViewState
import com.example.assignmenttest.util.ApiService
import com.example.assignmenttest.util.decodePoly
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext
import java.net.URL

class MainViewModel(context: Context): BaseViewModel(),KodeinAware{

    override val kodein by closestKodein(context)
    override val kodeinContext = kcontext(context)
    private val apiService by instance<ApiService>()
    val downloadResponse: MutableLiveData<ViewState<List<LatLng>>> = MutableLiveData()

    fun downloadUrl(strUrl: String?): List<LatLng> {
        val result = URL(strUrl).readText()
        val parser: Parser = Parser()
        val stringBuilder: StringBuilder = StringBuilder(result)
        val json: JsonObject = parser.parse(stringBuilder) as JsonObject
        // get to the correct element in JsonObject
        val routes = json.array<JsonObject>("routes")
        val points = routes!!["legs"]["steps"][0] as JsonArray<JsonObject>
        // For every element in the JsonArray, decode the polyline string and pass all points to a List
        val polypts = points.flatMap { decodePoly(it.obj("polyline")?.string("points")!!)  }
         return polypts
    }

    fun download(url: String) {
        viewModelScope.launch (Dispatchers.IO){
           val data =downloadUrl(url)
            withContext(Dispatchers.Main){
                downloadResponse.postValue(ViewState.Success(data))
            }
        }
    }


}