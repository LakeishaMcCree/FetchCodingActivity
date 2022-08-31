package com.example.fetchcodingactivity

import android.app.Application
import android.content.ClipData
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class FetchViewModel(app: Application): AndroidViewModel(app), CoroutineScope by MainScope() {
    val baseUrl = "https://fetch-hiring.s3.amazonaws.com/"

    private val _fetchItemsUpdated: MutableLiveData<Boolean> = MutableLiveData()
    val fetchItemsUpdated: LiveData<Boolean> = _fetchItemsUpdated

    var fetchItems: List<FetchItem> = listOf(
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0),
                FetchItem( 0, "", 0)
    )


    fun printData() {
        fetchItems.forEach {
            println("id: ${it.id} listId: ${it.listId} name: ${it.name}")
        }
    }

    fun getData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(APIService::class.java)

        launch(Dispatchers.IO) {
            val response = service.getList()
            fetchItems = response.filter { !it.name.isNullOrBlank() }
                .sortedBy { it.id }
                .sortedBy { it.listId }
            _fetchItemsUpdated.postValue(true)
        }
    }
}