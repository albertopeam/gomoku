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
    private var lineWidth: Float = 3f
    private var linePaint: Paint
    var cells: Int = 19

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.GridView,
            0, 0
        )?.apply {
            try {
                lineColor = getInt(R.styleable.GridView_line_color, lineColor)
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
            val totalLinesWidth = lineWidth * cells
            val distance = (height - totalLinesWidth) / (cells + 2)
            (1..cells + 1).forEach {
                val position = (it * distance) + (it * lineWidth)
                drawLine(distance, position, width.toFloat() - distance, position, linePaint)
                drawLine(position, distance, position, height.toFloat() - distance + lineWidth, linePaint)
            }
        }
    }
}