package com.lu.plate.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.core.os.HandlerCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.target.Target

class ImageUtil {

    companion object {
        private val mainHandler: Handler by lazy {
            HandlerCompat.createAsync(Looper.getMainLooper())
        }

        fun with(view: View): GlideWith {
            return GlideWith(view)
        }

        fun with(act: Activity): GlideWith {
            return GlideWith(act)
        }

        fun with(context: Context): GlideWith {
            return GlideWith(context)
        }

        fun with(frag: Fragment): GlideWith {
            return GlideWith(frag)
        }


        @Suppress("DEPRECATION")
        fun with(appFragment: android.app.Fragment): GlideWith {
            return GlideWith(appFragment)
        }

        private fun runOnUi(block: Runnable) {
            mainHandler.post(block)
        }
    }

    /**
     * wrap glide to load img
     */
    class GlideWith(var glideWith: RequestManager) {
        private var onFailFunc: OnLoadFailedListener? = null
        private var onReadyFunc: OnResourceReadyListener? = null
        private var onSetResFunc: OnSetResourceListener? = null

        constructor(context: Context) : this(Glide.with(context))
        constructor(view: View) : this(Glide.with(view))
        constructor(act: Activity) : this(Glide.with(act))
        constructor(frag: Fragment) : this(Glide.with(frag))

        @Suppress("DEPRECATION")
        constructor(appFragment: android.app.Fragment) : this(Glide.with(appFragment))

        fun onFailed(block: OnLoadFailedListener?): GlideWith {
            onFailFunc = block
            return this
        }

        fun onReady(block: OnResourceReadyListener?): GlideWith {
            onReadyFunc = block
            return this
        }

        fun onSetResFunc(block: OnSetResourceListener?): GlideWith {
            onSetResFunc = block
            return this
        }

        fun load(imageView: ImageView, uri: String) {
            glideWith
                .load(uri)
                .disallowHardwareConfig()
                .listener(GlideRequestListener(onReadyFunc, onFailFunc))
                .into(DrawableImageViewFunc(imageView, onSetResFunc))

        }

        fun load(imageView: ImageView, resId: Int) {
            glideWith
                .load(resId)
                .disallowHardwareConfig()
                .listener(GlideRequestListener(onReadyFunc, onFailFunc))
                .into(DrawableImageViewFunc(imageView, onSetResFunc))
        }


    }

    open class GlideRequestListener @JvmOverloads constructor(
        private var onResourceReadyListener: OnResourceReadyListener? = null,
        private var onLoadFailedListener: OnLoadFailedListener? = null
    ) : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            runOnUi {
                onLoadFailedListener?.onLoadFailed(e)
            }
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            runOnUi {
                onResourceReadyListener?.onResourceReady(resource)
            }
            return false
        }

    }


    open class DrawableImageViewFunc @JvmOverloads constructor(
        imageView: ImageView,
        private var onSetResFunc: OnSetResourceListener? = null
    ) : DrawableImageViewTarget(imageView) {

        override fun setResource(resource: Drawable?) {
            super.setResource(resource)
            onSetResFunc?.onSetResource(resource)
        }

    }

    fun interface OnResourceReadyListener {
        fun onResourceReady(resource: Drawable?)
    }

    fun interface OnLoadFailedListener {
        fun onLoadFailed(exception: Exception?)
    }

    fun interface OnSetResourceListener {
        fun onSetResource(resource: Drawable?)
    }


}