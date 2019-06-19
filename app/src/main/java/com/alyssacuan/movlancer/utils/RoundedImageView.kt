package com.alyssacuan.movlancer.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import com.alyssacuan.movlancer.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class RoundedImageView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet), Target {
    private var image : ImageView = ImageView(context)
    constructor(context: Context, img : ImageView) : this(context, null){
        this.image = img
    }

    private var drawable: Drawable? = null
        set(value) {
            field = value
            postInvalidate()
        }

    fun loadImage(url: String?) {
        if (url == null) {
            drawable = null
        } else {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.android_image_1)
                .error(R.drawable.android_image_2)
                .into(image)
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawable?.setBounds(0, 0, width, height)
        drawable?.draw(canvas)
    }

    override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
        drawable = placeHolderDrawable
    }

    override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        drawable = errorDrawable
    }

    override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
        val roundedDrawable = RoundedBitmapDrawableFactory.create(resources, bitmap)
        roundedDrawable.cornerRadius = (DEFAULT_RADIUS).toFloat()
        drawable = roundedDrawable
    }

    companion object {
        private const val DEFAULT_RADIUS = 40
    }
}