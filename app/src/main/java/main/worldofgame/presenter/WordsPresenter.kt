package main.worldofgame.presenter

import main.worldofgame.model.WordsService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordsPresenter(private val wordsView: WordsView) {

    var wordsService = WordsService()

    fun onGetRandomWord() {

        wordsService.getRandomWord().enqueue(object : Callback<Array<String>> {
            override fun onFailure(call: Call<Array<String>>, t: Throwable) {
                wordsView.showError()
            }

            override fun onResponse(call: Call<Array<String>>, response: Response<Array<String>>) {
                if (response.isSuccessful) {
                    wordsView.showRandomWordValue(response.body()?.get(0) ?: "")
                }
            }
        })
    }

    fun onSendRandomWord() {
        wordsView.showSendRandomWord()
    }
}