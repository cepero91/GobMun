package cu.infocap.gobmun.util

import android.os.Build
import android.text.Html
import android.widget.TextView

fun TextView.setCompatibilityHtmlText(htmlText: String){
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlText)
    }
}