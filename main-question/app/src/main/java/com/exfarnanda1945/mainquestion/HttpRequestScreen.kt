package com.exfarnanda1945.mainquestion

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

@Composable
fun HttpRequestScreen(modifier: Modifier = Modifier) {
    var listPost by remember {
        mutableStateOf(emptyList<ResponseItem>().toMutableList())
    }

    LaunchedEffect(true) {
        RetrofitInstance.api.getPost().enqueue(object : Callback<List<ResponseItem>> {
            override fun onResponse(
                call: Call<List<ResponseItem>>,
                response: retrofit2.Response<List<ResponseItem>>
            ) {
                val result = response.body()
                result?.let {
                    listPost = it.take(10).toMutableList()
                }
                println(result)
            }

            override fun onFailure(call: Call<List<ResponseItem>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(modifier.weight(1f)) {
                itemsIndexed(listPost) { index: Int, item: ResponseItem ->
                    Text("${index + 1}. $item")
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        ElevatedButton(onClick = {
                            val copyList = listPost.map { it }.toMutableList()
                            copyList.removeAt(index)
                            listPost = copyList
                        }) {
                            Text("Hapus")
                        }
                        ElevatedButton(onClick = {
                            listPost = listPost.mapIndexed { i: Int, responseItem: ResponseItem ->
                                if (index == i) responseItem.copy(body = "") else responseItem
                            }.toMutableList()
                        }) {
                            Text("Hapus Objek Body")
                        }
                    }
                    Spacer(Modifier.height(10.dp))
                }
            }
        }
    }

}

data class ResponseItem(

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "body")
    val body: String? = null,

    @Json(name = "userId")
    val userId: Int? = null
)

interface ApiService {
    @GET("/posts")
    fun getPost(): Call<List<ResponseItem>>
}

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/") // Base URL
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().addLast(
                        KotlinJsonAdapterFactory()
                    ).build()
                )
            ) // For JSON deserialization
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}