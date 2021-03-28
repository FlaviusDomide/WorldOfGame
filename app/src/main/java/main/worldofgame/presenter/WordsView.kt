package main.worldofgame.presenter

interface WordsView {
    fun showRandomWordValue(word: String)
    fun showSendRandomWord()
    fun showError()
}