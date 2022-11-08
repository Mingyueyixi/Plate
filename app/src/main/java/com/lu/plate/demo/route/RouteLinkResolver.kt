package com.lu.plate.demo.route

import android.net.Uri

class RouteLinkResolver {

    companion object {

        fun buildRouteLink(path: String, vararg params: Pair<String, String>): Uri? {
            return Uri.Builder()
                .authority(AppLinkRouter.AUTHORITY)
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
            return buildRouteLink(RoutePath.APP_WEB, *exts)
        }

        fun buildAppMainLink(subPage: String): Uri? {
            return buildRouteLink(
                RoutePath.APP_MAIN,
                Pair(LinkParam.KEY_SUB_PAGE, subPage)
            )
        }
    }

    class RoutePath {
        companion object {
            const val APP_WEB = "/app/web"
            const val APP_MAIN = "/app/main"
        }
    }

    class LinkParam {
        companion object {
            const val KEY_SUB_PAGE = "subPage"
        }
    }
}