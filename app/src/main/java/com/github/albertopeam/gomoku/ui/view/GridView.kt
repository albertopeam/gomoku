package com.github.albertopeam.gomoku.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.github.albertopeam.gomoku.R
import kotlin.math.min

class GridView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var lineColor: Int = Color.BLACK
    private var divisions: Int = 2
    private var lineWidth: Float = 3f
    private var linePaint: Paint

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.GridView,
            0, 0
        )?.apply {
            try {
                lineColor = getInt(R.styleable.GridView_line_color, lineColor)
                divisions = getInt(R.styleable.GridView_divisions, divisions)
                lineWidth = getFloat(R.styleable.GridView_line_width, lineWidth)
            } finally {
                recycle()
            }
        }
        linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = lineWidth
            color = lineColor
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        val size = min(width, height)
        val newSizeSpec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY)
        setMeasuredDimension(newSizeSpec, newSizeSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            val width = measuredWidth
            val height = measuredHeight
            val totalLinesWidth = lineWidth * divisions
            val distance = (height - totalLinesWidth) / divisions
            (1 until divisions).forEach {
                val position = it * distance + it * lineWidth
                drawLine(0f, position, width.toFloat(), position, linePaint)
                drawLine(position, 0f, position, height.toFloat(), linePaint)
            }

        }
    }
}