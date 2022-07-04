package com.example.retrofitdemo.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitdemo.R
import com.example.retrofitdemo.adopters.ApiAdopter
import com.example.retrofitdemo.apiService.RetrofitsClient
import com.example.retrofitdemo.models.APIData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiTaskActivity : AppCompatActivity() {
    lateinit var adopter: ApiAdopter
    var arrayList = ArrayList<APIData>()
    lateinit var recyclerView : RecyclerView
    var dataList = ArrayList<String>()
    var mapList = ArrayList<Map<String,Double>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_task)
        recyclerView = findViewById(R.id.recylerViewApidemo)
        getData()
        getDataUsingObject()
    }

    private fun getDataUsingObject() {

    }

    private fun getData() {
        val data = RetrofitsClient.getInstance().getData("2018-01-01","2018-06-01","EUR")
       data.enqueue(object:Callback<APIData>{
           override fun onResponse(call: Call<APIData>, response: Response<APIData>) {
               val responses = response.body()
               if (responses != null) {
                   Log.d("Data", responses.toString())

                   for (i in responses.rates.keys){
                       dataList.add(i)

                   }
                   for(i in dataList){
                       mapList.add(responses.rates[i]!!)
                   }

                   arrayList.add(responses)
                   adopter = ApiAdopter(mapList)
                   recyclerView.adapter = adopter

               }
               Log.d("Data", response.toString())
                         }

           override fun onFailure(call: Call<APIData>, t: Throwable) {
               Log.d("Data",t.message.toString())
           }

       })
    }
}