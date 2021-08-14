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
import com.github.albertopeam.gomoku.domain.BoardState
import com.github.albertopeam.gomoku.domain.Player
import com.github.albertopeam.gomoku.domain.Position
import kotlin.math.min
import kotlin.math.roundToInt

//TODO: GameImpl -> Gomoku rules? interface
//TODO: GomokuRules. need algorithm improvements, three double for loops, first one can be introduced in third one first level.
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
    var board: BoardState? = null
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
        drawGrid(canvas)
        drawPieces(canvas, board)
    }

    internal fun position(event: MotionEvent): Pair<Int, Int> {
        val posX = event.x
        val posY = event.y
        val cellSide = height / (cells + 1)
        val row = (posY / cellSide).roundToInt() - 1
        val column = (posX / cellSide).roundToInt() - 1

        return Pair(row, column)
    }

    private fun drawGrid(canvas: Canvas?) {
        canvas?.apply {
            val width = measuredWidth
            val height = measuredHeight
            val distance = cellSize()
            (1..cells).forEach {
                val position = (it * distance) + (it * lineWidth)
                drawLine(distance + 1, position, width.toFloat() - distance, position, linePaint) //draw horizontal
                drawLine(position, distance + 1, position, height.toFloat() - distance, linePaint) //draw vertical
            }
        }
    }

    private fun drawPieces(canvas: Canvas?, boardState: BoardState?) {
        boardState?.let { board ->
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

    private fun drawPiece(canvas: Canvas?, col: Int, row: Int, paint: Paint) {
        val xPos = col + 1
        val yPos = row + 1
        canvas?.apply {
            val distance = cellSize()
            val radiusPadding = lineWidth
            val radius = (distance - (radiusPadding)) / 2.5f
            val xCenter = (distance * xPos) + (lineWidth * xPos)// + radius + radiusPadding
            val yCenter = (distance * yPos) + (lineWidth * yPos)// + radius + radiusPadding
            drawCircle(xCenter, yCenter, radius, paint)
        }
    }

    private fun cellSize(): Float {
        val totalLinesWidth = lineWidth * cells
        val internalSize = height - totalLinesWidth
        return internalSize / (cells + 1)
    }
}