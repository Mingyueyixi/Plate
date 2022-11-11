package com.lu.plate.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition

class GlideUtil {

    companion object {

        fun with(view: View): LoadWith {
            return LoadWith(view)
        }

        fun with(act: Activity): LoadWith {
            return LoadWith(act)
        }

        fun with(context: Context): LoadWith {
            return LoadWith(context)
        }

        fun with(frag: Fragment): LoadWith {
            return LoadWith(frag)
        }

        fun with(appFragment: android.app.Fragment): LoadWith {
            return LoadWith(appFragment)
        }

    }

    class LoadWith(var glideWith: RequestManager) {
        private var onFailFunc: OnLoadFailedListener? = null
        private var onReadyFunc: OnResourceReadyListener? = null
        private var onSetResFunc: OnSetResourceListener? = null

        constructor(context: Context) : this(Glide.with(context))
        constructor(view: View) : this(Glide.with(view))
        constructor(act: Activity) : this(Glide.with(act))
        constructor(frag: Fragment) : this(Glide.with(frag))
        constructor(appFragment: android.app.Fragment) : this(Glide.with(appFragment))

        fun onFailed(block: OnLoadFailedListener?): LoadWith {
            onFailFunc = block
            return this
        }

        fun onReady(block: OnResourceReadyListener?): LoadWith {
            onReadyFunc = block
            return this
        }

        fun onSetResFunc(block: OnSetResourceListener?): LoadWith {
            onSetResFunc = block
            return this
        }

        fun load(imageView: ImageView, uri: String) {
            Glide.with(imageView)
                .load(uri)
                .disallowHardwareConfig()
                .into(DrawableImageViewFunc(imageView, onFailFunc, onReadyFunc, onSetResFunc))
        }

        fun load(imageView: ImageView, resId: Int) {
            Glide.with(imageView)
                .load(resId)
                .disallowHardwareConfig()
                .into(DrawableImageViewFunc(imageView, onFailFunc, onReadyFunc, onSetResFunc))

        }

    }

    open class DrawableImageViewFunc @JvmOverloads constructor(
        imageView: ImageView,
        var onFailedFunc: OnLoadFailedListener?,
        var onReadyFunc: OnResourceReadyListener?,
        var onSetResFunc: OnSetResourceListener?
    ) : DrawableImageViewTarget(imageView) {
        override fun onLoadFailed(errorDrawable: Drawable?) {
            super.onLoadFailed(errorDrawable)
            onFailedFunc?.onLoadFailed(errorDrawable)
        }

        override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
            super.onResourceReady(resource, transition)
            onReadyFunc?.onResourceReady(resource, transition)
        }

        override fun setResource(resource: Drawable?) {
            super.setResource(resource)
            onSetResFunc?.onSetResource(resource)
        }

    }

    fun interface OnResourceReadyListener {
        fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?)
    }

    fun interface OnLoadFailedListener {
        fun onLoadFailed(errorDrawable: Drawable?)
    }

    fun interface OnSetResourceListener {
        fun onSetResource(resource: Drawable?)
    }


}