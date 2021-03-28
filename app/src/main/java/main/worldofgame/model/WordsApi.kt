package main.worldofgame.model

import retrofit2.Call
import retrofit2.http.GET

interface WordsApi {

    @GET("word?number=1")
    fun getRandomWord(): Call<Array<String>>
}