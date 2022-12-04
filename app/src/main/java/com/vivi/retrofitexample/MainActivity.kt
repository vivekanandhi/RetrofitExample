package com.vivi.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var listview : ListView
    private lateinit var heroes: Array<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://mocki.io/v1/0c0939ad-c7a1-4d6c-bec9-bda2abcbf245

        listview = findViewById(R.id.listView)
        var retrofit = Retrofit.Builder().baseUrl("https://mocki.io/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(SuperheroAPI::class.java)

        api.getHeroes().enqueue(object : Callback<Heros> {

            override fun onFailure(call: retrofit2.Call<Heros>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                Log.e("Vivi", "${t.message}")
            }

            override fun onResponse(
                call: retrofit2.Call<Heros>,
                response: Response<Heros>
            ) {
                val heroList = response.body()
                heroList?.let {

                    heroes = arrayOf(it.name)



                }

                listview.adapter = ArrayAdapter(
                    applicationContext, android.R.layout.simple_list_item_1,
                    heroes)
            }
        })

    }
}