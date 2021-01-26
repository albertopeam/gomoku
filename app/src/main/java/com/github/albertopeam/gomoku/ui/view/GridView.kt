package com.github.albertopeam.gomoku.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.github.albertopeam.gomoku.R
import com.github.albertopeam.gomoku.domain.Board
import com.github.albertopeam.gomoku.domain.Player
import com.github.albertopeam.gomoku.domain.Position
import kotlin.math.min
import kotlin.math.roundToInt

//TODO: migrate to non squarish board
//TODO: fix board lines start and end(small breaks)
class GridView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var lineColor: Int = Color.BLACK
    private var lineWidth: Float = 3f
    private var linePaint: Paint
    private var whitePiecePaint: Paint
    private var blackPiecePaint: Paint
    private val cells: Int
        get() {
            board?.let { return it.rows }
            return 0
        }
    var board: Board? = null
        set(value) {
            field = value
            invalidate()
        }

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
        whitePiecePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = Color.WHITE
        }
        blackPiecePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL
            color = Color.BLACK
        }
        setOnTouchListener { view, motionEvent ->
            board?.let { board ->
                if (motionEvent.actionMasked == MotionEvent.ACTION_UP) {
                    val posX = motionEvent.x
                    val posY = motionEvent.y
                    val column = (posX / cellSize()).roundToInt() - 1
                    val row = (posY / cellSize()).roundToInt() - 1
                    board.place(Position(row, column), Player.WHITE)
                    invalidate()
                }
            }
            view.performClick()
            return@setOnTouchListener true
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

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        board?.let { board ->
            drawGrid(canvas)
            (0 until cells).forEach { col ->
                (0 until cells).forEach { row ->
                    val player = board.get(Position(row, col))
                    if (player == Player.WHITE) {
                        drawPiece(canvas, col, row, whitePiecePaint)
                    } else if (player == Player.BLACK) {
                        drawPiece(canvas, col, row, blackPiecePaint)
                    }
                }
            }
        }
    }

    private fun drawGrid(canvas: Canvas?) {
        canvas?.apply {
            val width = measuredWidth
            val height = measuredHeight
            val distance = cellSize()
            (1..cells + 1).forEach {
                val position = (it * distance) + (it * lineWidth)
                drawLine(distance, position, width.toFloat() - distance, position, linePaint)
                drawLine(position, distance, position, height.toFloat() - distance + lineWidth, linePaint)
            }
        }
    }

    private fun drawPiece(canvas: Canvas?, col: Int, row: Int, paint: Paint) {
        val xPos = col + 1
        val yPos = row + 1
        canvas?.apply {
            val distance = cellSize()
            val radiusPadding = lineWidth
            val radius = (distance - (radiusPadding)) / 2.5f
            val xCenter = (distance * xPos) + (lineWidth * xPos)// + radius + radiusPadding
            val yCenter = (distance * yPos) + (lineWidth * yPos)// + radius + radiusPadding
            drawCircle(xCenter, yCenter , radius, paint)
        }
    }

    private fun cellSize(): Float {
        val totalLinesWidth = lineWidth * cells
        return (height - totalLinesWidth) / (cells + 1)
    }
}