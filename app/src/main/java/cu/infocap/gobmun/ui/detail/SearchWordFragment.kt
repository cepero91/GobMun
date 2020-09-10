package cu.infocap.gobmun.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.text.bold
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import cu.infocap.gobmun.R
import cu.infocap.gobmun.databinding.FragmentSearchWordBinding
import cu.infocap.gobmun.domain.model.Data
import cu.infocap.gobmun.util.Constants
import cu.infocap.gobmun.util.setCompatibilityHtmlText


class SearchWordFragment : Fragment() {

    private lateinit var binding: FragmentSearchWordBinding
    private var data: Data? = null
    private var spannableText: SpannableStringBuilder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            data = it.getParcelable(Constants.EXTRA_DATA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_search_word, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        val menuItem = menu.findItem(R.id.action_search_word)
        menuItem.isVisible = true
        val searchView: SearchView = menuItem.actionView as SearchView
        initSearchView(searchView)
        super.onPrepareOptionsMenu(menu)
    }

    private fun initSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                findAllWord(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                   removeSpan(SpannableString(binding.tvData.text))
                }
                return true
            }
        })
    }

    private fun findAllWord(query: String?) {
        query?.let {
            if (spannableText?.contains(it) == true) {
                highlightString(query)
            }
        }
    }

    private fun initUI() {
        data?.let { longText ->
            spannableText = SpannableStringBuilder()
            spannableText?.let {
                it.append(longText.description).append("\n")
                longText.childrens.forEach { dataChild ->
                    it.bold { append(dataChild.name) }
                            .append(dataChild.description)
                            .append("\n")
                }
                binding.tvData.setCompatibilityHtmlText(it.toString())
            }
        }
    }

    private fun highlightString(input: String) {
        //Get the text from text view and create a spannable string
        val spannableString = SpannableString(binding.tvData.text)
        //Get the previous spans and remove them
        removeSpan(spannableString)

        //Search for all occurrences of the keyword in the string
        var indexOfKeyword = spannableString.toString().indexOf(input)
        while (indexOfKeyword != -1) {
            //Create a background color span on the keyword
            spannableString.setSpan(BackgroundColorSpan(Color.YELLOW), indexOfKeyword, indexOfKeyword + input.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

            //Get the next index of the keyword
            indexOfKeyword = spannableString.toString().indexOf(input, indexOfKeyword + input.length)
        }

        binding.tvData.text = spannableString
    }

    private fun removeSpan(spannableString: SpannableString) {
        val backgroundSpans = spannableString.getSpans(0, spannableString.length, BackgroundColorSpan::class.java)
        for (span in backgroundSpans) {
            spannableString.removeSpan(span)
        }
    }


}