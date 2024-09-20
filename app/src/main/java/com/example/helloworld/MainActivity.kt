package com.example.helloworld

import android.os.Bundle
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.helloworld.ui.theme.HelloWorldTheme


class MainActivity : ComponentActivity() {

    // Init block for initialization logging
    init {
        Log.d("MainActivity", "MainActivity is initialized")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // if using XML layout
//        Log.d("MainActivity", "onCreate called")
//        enableEdgeToEdge()
        val webView: WebView = findViewById(R.id.webView)
        webView.webViewClient = WebViewClient()  // Ensures web pages open inside WebView

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true  // Enable JavaScript if needed

        // Add JavaScript interface
        webView.addJavascriptInterface(WebAppInterface(this), "AndroidFunction")

        // Load a URL
        webView.loadUrl("http://192.168.3.87:2000/cep-telefonu.html")
//        setContent {
//            HelloWorldTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }

    }

}

class WebAppInterface(private val activity: ComponentActivity) {

    // This method will be called from JavaScript
    @JavascriptInterface
    fun triggerNativeFunction(message: String) {
        Log.d("WebView", "Message from JavaScript: $message")

        // Perform any native function here, like showing a Toast, starting an activity, etc.
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!!!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HelloWorldTheme {
        Greeting("Android")
    }
}