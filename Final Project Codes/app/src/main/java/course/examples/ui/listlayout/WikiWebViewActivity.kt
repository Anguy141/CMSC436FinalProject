package course.examples.ui.listlayout

import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Web View based off of Example Lecture Code
// Loads WikiHow web page in Web View
class WikiWebViewActivity : AppCompatActivity() {

    private lateinit var mWebView: WebView

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_page)

        val wikiURL : String? = this.intent.getStringExtra("HobbyHref")

        mWebView = findViewById(R.id.webview)

        // Set a kind of listener on the WebView so the WebView can intercept URL loading requests if it wants to
        // Prevents the user from following any urls in the web view
        mWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return true
            }

        }

        // Uncomment this code to include pictures in the web view (Unfortunately allowing javascript brings ads and clutter so it is disabled by default)
        // mWebView.settings.javaScriptEnabled = true

        if (wikiURL != null) {
            mWebView.loadUrl(wikiURL)
        }
        else {
            Toast.makeText(applicationContext, "The Web View could not find this Hobby :(", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}
