package ru.mikaeliv.canvas

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.ContextCompat

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
    private var previousAngle: Float = 0f
    private var sweepAngle: Float = 0f

    override fun onDraw(canvas: Canvas?) {
        canvas ?: return

        paint.apply {
            color = ContextCompat.getColor(this@ProgressView.context, R.color.colorLightBlue)
            strokeWidth = 20f
            style = Paint.Style.STROKE
        }

        canvas.drawArc(rectf, 145f, 250f, false, paint)

        paint.color = ContextCompat.getColor(context, R.color.colorDarkBlue)

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
        ValueAnimator.ofFloat(previousAngle, p * ONE_PERCENT).apply {
            duration = 650
            interpolator = LinearInterpolator()
            addUpdateListener {
                sweepAngle = it.animatedValue as Float
                this@ProgressView.invalidate()
            }
        }.start()
        previousAngle = p * ONE_PERCENT
    }

    companion object {
        const val ONE_PERCENT = 2.5f
    }
}