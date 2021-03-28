package main.worldofgame.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import main.worldofgame.R
import main.worldofgame.presenter.WordsPresenter
import main.worldofgame.presenter.WordsView
import main.worldofgame.util.Constants
import main.worldofgame.util.SharedPreferencesManager

class MainActivity : AppCompatActivity(), WordsView {
    lateinit var randomWordButton: MaterialButton
    lateinit var randomWordText: MaterialTextView
    lateinit var sharedPreferencesManager: SharedPreferencesManager
    lateinit var presenter: WordsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        randomWordButton = findViewById(R.id.get_random_word_btn)
        randomWordText = findViewById(R.id.random_word_txt)
        presenter = WordsPresenter(this)
        sharedPreferencesManager = SharedPreferencesManager(this)

        randomWordButton.setOnClickListener {
            presenter.onGetRandomWord()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_send_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_send_random_text -> {
            presenter.onSendRandomWord()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun showRandomWordValue(word: String) {
        randomWordText.text = word
        sharedPreferencesManager.putString(Constants.SHARED_PREF_SEND_TEXT, word)
    }

    override fun showSendRandomWord() {
        val word = sharedPreferencesManager.getString(Constants.SHARED_PREF_SEND_TEXT)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, word)
            type = Constants.TEXT_PLAIN
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun showError() {
        Toast.makeText(this, resources.getString(R.string.failed_random_word), Toast.LENGTH_SHORT)
                .show()
    }
}