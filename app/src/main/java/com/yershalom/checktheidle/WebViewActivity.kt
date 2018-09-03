package com.yershalom.checktheidle

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewActivity: AppCompatActivity() {
    val WEBSITE_ADDRESS = "website_address"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url: String = intent.getStringExtra(WEBSITE_ADDRESS)
        if (url.isEmpty()) finish()

        setContentView(R.layout.activity_webview)
        val webView: WebView = findViewById(R.id.wv_post)
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
    }
}