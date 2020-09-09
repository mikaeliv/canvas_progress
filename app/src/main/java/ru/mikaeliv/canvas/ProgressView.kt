package ru.mikaeliv.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class ProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private val paint by lazy { Paint() }
    private val rectf by lazy {
        RectF(
            (this.width / 2 - 200).toFloat(),
            (this.height / 2 - 200).toFloat(),
            (this.width / 2 + 200).toFloat(),
            (this.height / 2 + 200).toFloat()
        )
    }
    private var sweepAngle: Float = 0f

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        paint.apply {
            color = Color.RED
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }

        canvas.drawArc(rectf, 145f, 250f, false, paint)

        paint.color = Color.GREEN

        canvas.drawArc(rectf, 145f, sweepAngle, false, paint)

        paint.color = Color.WHITE

        canvas.drawLine(
            (this.width / 2 - 200).toFloat(),
            (this.height / 2 + 115).toFloat(),
            (this.width / 2 + 200).toFloat(),
            (this.height / 2 + 115).toFloat(),
            paint
        )
    }

    fun setProgress(progress: Float) {
        val p = if (progress > 100f) 100f else progress
        sweepAngle = p * ONE_PERCENT
        this.invalidate()
    }

    companion object {
        const val ONE_PERCENT = 2.5f
    }
}