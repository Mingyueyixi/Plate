package com.lu.plate.demo.route

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.blankj.utilcode.util.ActivityUtils
import com.lu.plate.demo.MainActivity
import com.lu.plate.demo.util.log.LogUtil
import com.lu.plate.demo.web.WebViewActivity


//路由跳转
object AppLinkRouter {
    const val AUTHORITY = "schemas.plate.demo"

    fun route(context: Context = ActivityUtils.getTopActivity(), link: String?) {
        if (link.isNullOrBlank()) {
            return
        }
        var uri = Uri.parse(link)
        var scheme = uri.scheme
        when (scheme) {
            "http", "https" -> {
                //webview 或其他链接
                //handleWebViewLink(context, uri)
                visitWebViewPage(context, link)
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
            RouteLinkResolver.RoutePath.APP_WEB -> {
                handleWebViewLink(context, uri)
            }
            RouteLinkResolver.RoutePath.APP_MAIN -> handleAppMainLink(context, uri)
            else -> {
                LogUtil.e("router handle not match path: $path")
            }
        }
    }

    private fun visitWebViewPage(context: Context, webUrl: String?) {
        if (webUrl.isNullOrBlank()) {
            LogUtil.e("webUrl is Empty")
            return
        }
        val intent = Intent(context, WebViewActivity::class.java)
        //fix: some mobile model(slow api) need add a flag to open Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra(Extra.KEY_WEB_URL, webUrl)
        context.startActivity(intent)
    }

    private fun handleWebViewLink(context: Context, uri: Uri) {
        val webUrl = uri.getQueryParameter("url")
        visitWebViewPage(context, webUrl)
    }

    private fun handleAppMainLink(context: Context, uri: Uri) {
        val act = ActivityUtils.getTopActivity()
        if (act is MainActivity) {
            uri.getQueryParameter(RouteLinkResolver.LinkParam.KEY_SUB_PAGE)?.let {
                act.routeFragment(it)
            }
        } else {
            val intent = Intent(act, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            act.startActivity(intent)
        }

    }

    class Extra {
        companion object {
            const val KEY_WEB_URL = "INTENT_WEB_URL"
        }
    }


}