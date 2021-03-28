package main.worldofgame.model

import main.worldofgame.util.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WordsService {
    private var api: WordsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(WordsApi::class.java)
    }

    fun getRandomWord(): Call<Array<String>> {
        return api.getRandomWord()
    }
}