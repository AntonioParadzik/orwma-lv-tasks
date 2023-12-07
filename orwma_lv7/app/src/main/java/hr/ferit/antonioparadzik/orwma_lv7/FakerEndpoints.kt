package hr.ferit.antonioparadzik.orwma_lv7

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FakerEndpoints {
    @GET("/api/v1/products.json")
    fun getMakeup(@Query("brand") brand:String):Call<ArrayList<Makeup>>


}