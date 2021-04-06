package com.devlong.asigntaskapp

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/*
* Author by Admin on 28/03/21
*/

class SquareImageView : AppCompatImageView {
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec)
    }
}