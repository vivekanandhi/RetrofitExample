package com.vivi.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import retrofit2.Call
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

        //https://mocki.io/v1/61eb3e08-57ae-4401-b398-d29295b2832b

        listview = findViewById(R.id.listView)
        var retrofit = Retrofit.Builder().baseUrl(Utils.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(SuperheroAPI::class.java)

        api.getHeroes().enqueue(object : Callback<List<Heros>>{

            override fun onFailure(call: Call<List<Heros>>, t: Throwable) {
                Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                Log.e("Vivi", "${t.message}")
            }

            override fun onResponse(call: Call<List<Heros>>,
                                    response: Response<List<Heros>>) {
                val heroList: List<Heros>? = response.body()

                heroList?.let {
                    heroes = arrayOfNulls<String>(it.size)
                    for (i in it.indices){
                        heroes[i]=heroList[i].name
                    }
                }


                listview.adapter = ArrayAdapter(
                    applicationContext, android.R.layout.simple_list_item_1,
                    heroes)
            }
        })

    }
}