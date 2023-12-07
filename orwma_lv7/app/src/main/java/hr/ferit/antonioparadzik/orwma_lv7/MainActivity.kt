package hr.ferit.antonioparadzik.orwma_lv7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView=findViewById<RecyclerView>(R.id.makeupList)
        recyclerView.apply {
            layoutManager=LinearLayoutManager(this@MainActivity)
            adapter=MakeupRecyclerAdapter(ArrayList())
        }
        val editText=findViewById<EditText>(R.id.editText)
        val service=ServiceBuilder.buildService(FakerEndpoints::class.java)
        findViewById<Button>(R.id.button).setOnClickListener {
            val brand=editText.text.toString()
            val call=service.getMakeup(brand)

            call.enqueue(
                object:Callback<ArrayList<Makeup>>{
                    override fun onResponse(
                        call: Call<ArrayList<Makeup>>,
                        response: Response<ArrayList<Makeup>>
                    ) {
                        if(response.isSuccessful){
                            recyclerView.apply {
                                layoutManager=LinearLayoutManager(this@MainActivity)
                                adapter=MakeupRecyclerAdapter(response.body()!!)
                            }
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Makeup>>, t: Throwable) {
                        Log.e("MainActivity", t.message.toString())
                    }

                }
            )
        }


    }
}