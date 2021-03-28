package main.worldofgame

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import main.worldofgame.model.WordsService
import main.worldofgame.presenter.WordsPresenter
import main.worldofgame.presenter.WordsView
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock

class WordsPresenterTest {

    lateinit var view: WordsView

    @Mock
    var wordsService: WordsService? = null

    @InjectMocks
    var presenter: WordsPresenter? = null

    @Test
    fun `view must show show send options to the user in order to be able to send the random word`() {
        //GIVEN
        wordsService = WordsService()
        view = mock()
        presenter = WordsPresenter(view)

        //WHEN
        presenter?.onSendRandomWord()

        //THEN
        verify(view).showSendRandomWord()
    }
}