package com.lu.plate.demo.web

import android.net.Uri
import android.net.http.SslError
import android.os.Bundle
import android.view.View
import android.webkit.*
import com.blankj.utilcode.util.GsonUtils
import com.lu.plate.demo.base.BaseActivity
import com.lu.plate.demo.databinding.ActivityWebBinding
import com.lu.plate.demo.route.AppLinkRouter
import com.lu.plate.demo.util.log.LogUtil

class WebViewActivity : BaseActivity() {
    private lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)

        var webUrl = intent.getStringExtra(AppLinkRouter.Extra.KEY_WEB_URL)
        if (webUrl.isNullOrBlank()) {
            LogUtil.e("Error, webUrl is empty")
            finish()
        } else {
            initWebView()
            binding.webViewEle.loadUrl(webUrl)
        }

    }

    private fun initWebView() {
        binding.webViewEle.apply {
            setLayerType(View.LAYER_TYPE_SOFTWARE, null)

            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.databaseEnabled = true
            settings.javaScriptEnabled = true
            settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
            settings.mediaPlaybackRequiresUserGesture = true
            settings.pluginState = WebSettings.PluginState.ON
            settings.domStorageEnabled = true
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK


            webViewClient = object : WebViewClient() {


                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    val uri = Uri.parse(url)
                    when (uri.scheme) {
                        "http", "https", "file" -> return super.shouldOverrideUrlLoading(view, url)
                    }
                    //app link或其他链接，不加载
                    return true
                }


                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    LogUtil.d("onPageFinished: $url")
                }

                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                    LogUtil.e("receive error:", description, errorCode, failingUrl)
                }

                override fun onReceivedSslError(
                    view: WebView?,
                    handler: SslErrorHandler?,
                    error: SslError?
                ) {
                    LogUtil.i("ssl error", error?.url, error?.primaryError)
                    handler?.proceed()
//                    super.onReceivedSslError(view, handler, error)
                }

                override fun onRenderProcessGone(
                    view: WebView?,
                    detail: RenderProcessGoneDetail?
                ): Boolean {
                    LogUtil.e("onRenderProcessGone:", detail)
                    return super.onRenderProcessGone(view, detail)
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(
                    message: String?,
                    lineNumber: Int,
                    sourceID: String?
                ) {
                    super.onConsoleMessage(message, lineNumber, sourceID)
                    LogUtil.i(message, "line:$lineNumber", "source:$sourceID")
                }
            }
        }
    }
}