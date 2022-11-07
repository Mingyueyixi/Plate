package com.lu.plate.demo.route

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.blankj.utilcode.util.ActivityUtils
import com.lu.plate.demo.util.log.LogUtil
import com.lu.plate.demo.web.WebViewActivity


//路由跳转
object AppLinkRouter {
    const val AUTHORITY = "schemas.plate.demo"

    fun buildAppLink(path: String, vararg params: Pair<String, String>): Uri? {
        return Uri.Builder()
            .authority(AUTHORITY)
            .scheme("app")
            .path(path).apply {
                for (p in params) {
                    appendQueryParameter(p.first, p.second)
                }
            }.build()
    }

    fun buildAppWebLink(url: String, vararg params: Pair<String, String>): Uri? {
        val exts = arrayListOf(*params).let {
            it.add(Pair("url", url))
            return@let it.toTypedArray()
        }
        return buildAppLink(RoutePath.APP_WEB, *exts)
    }

    fun route(context: Context = ActivityUtils.getTopActivity(), link: String?) {
        if (link.isNullOrBlank()) {
            return
        }
        var uri = Uri.parse(link)
        var scheme = uri.scheme
        when (scheme) {
            "http", "https" -> {
                //webview 或其他链接
            }
            "app" -> {
                //app内部链接
                handleAppLink(context, uri)
            }
        }
    }


    private fun handleAppLink(context: Context, uri: Uri) {
        if (AUTHORITY != uri.authority) {
            LogUtil.w("host not match,can't handle this uri: $uri")
            return
        }
        var path = uri.path
        when (path) {
            RoutePath.APP_WEB -> {
                handleWebViewLink(context, uri)
            }
            else -> {
                LogUtil.e("router handle not match path: $path")
            }
        }
    }

    private fun handleWebViewLink(context: Context, uri: Uri) {
        val webUrl = uri.getQueryParameter("url")
        val intent = Intent(context, WebViewActivity::class.java)
        //fix: some mobile model(slow api) need add a flag to open Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(Extra.KEY_WEB_URL, webUrl)
        context.startActivity(intent)
    }

    class Extra {
        companion object {
            const val KEY_WEB_URL = "INTENT_WEB_URL"
        }
    }

    class RoutePath {
        companion object {
            const val APP_WEB = "/app/web"
        }
    }

}