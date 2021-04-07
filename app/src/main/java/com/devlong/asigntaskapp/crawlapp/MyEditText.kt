package com.devlong.asigntaskapp.crawlapp

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.EditText
import com.devlong.asigntaskapp.R

/*
* Author by Admin on 07/04/21
*/

@SuppressLint("AppCompatCustomView")
class MyEditText : EditText{
    private lateinit var icon:Bitmap

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs){
        init(attrs)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ){
        init(attrs)
    }
    private fun init(attr: AttributeSet?) {
        val type = context.obtainStyledAttributes(attr, R.styleable.MyEditText)
        var iconid = type.getResourceId(R.styleable.MyEditText_iconEDT,R.drawable.edit_icon)
        icon = BitmapFactory.decodeResource(context.resources,iconid)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }
}