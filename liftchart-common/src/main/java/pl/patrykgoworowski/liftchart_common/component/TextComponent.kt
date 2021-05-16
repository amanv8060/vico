package pl.patrykgoworowski.liftchart_common.component

import android.graphics.*
import android.graphics.Color.DKGRAY
import android.graphics.Paint.Align.*
import android.text.TextPaint
import pl.patrykgoworowski.liftchart_common.component.dimension.DefaultPadding
import pl.patrykgoworowski.liftchart_common.component.dimension.Padding
import pl.patrykgoworowski.liftchart_common.extension.sp
import pl.patrykgoworowski.liftchart_common.path.RectShape
import pl.patrykgoworowski.liftchart_common.path.Shape

public open class TextComponent(
    shape: Shape = RectShape(),
    color: Int = Color.TRANSPARENT,
    textColor: Int = DKGRAY,
    textSize: Float = 12f.sp
) : Component(shape, color), Padding by DefaultPadding() {

    public val textPaint = TextPaint()

    public var isLTR: Boolean = true
    public var textColor: Int by textPaint::color
    public var textSize: Float by textPaint::textSize
    public var textAlign: Paint.Align by textPaint::textAlign
    public var typeface: Typeface by textPaint::typeface

    private val measurementBounds = Rect()

    init {
        textPaint.color = textColor
        textPaint.textSize = textSize
    }

    public fun drawTextCenteredVertically(
        canvas: Canvas,
        text: String,
        textX: Float,
        textY: Float
    ) {

        getTextBounds(text)

        val adjustedX = when (textAlign) {
            LEFT -> textX + padding.getLeft(isLTR)
            CENTER -> textX
            RIGHT -> textX - padding.getRight(isLTR)
        }

        val baseLeft = when (textAlign) {
            LEFT -> adjustedX
            CENTER -> adjustedX - (measurementBounds.width() / 2)
            RIGHT -> adjustedX - measurementBounds.width()
        }

        draw(
            canvas = canvas,
            left = baseLeft - padding.getLeft(isLTR),
            top = textY - ((measurementBounds.height() / 2) + padding.top),
            right = baseLeft + measurementBounds.width() + padding.getRight(isLTR),
            bottom = textY + ((measurementBounds.height() / 2) + padding.bottom)
        )

        getTextBounds(TEXT_MEASUREMENT_CHAR)

        val centeredY = textY + (measurementBounds.height() / 2)
        canvas.drawText(
            text,
            adjustedX,
            centeredY,
            textPaint
        )
    }

    public fun getTextBounds(text: String, outBounds: Rect = measurementBounds): Rect {
        textPaint.getTextBounds(text, 0, text.length, outBounds)
        return outBounds
    }

    public fun getWidth(text: String, outBounds: Rect = measurementBounds): Float {
        getTextBounds(text, outBounds)
        return outBounds.width() + padding.start + padding.end
    }

    public fun getHeight(text: String = TEXT_MEASUREMENT_CHAR, outBounds: Rect = measurementBounds): Float {
        getTextBounds(text, outBounds)
        return outBounds.height() + padding.top + padding.bottom
    }

    companion object {
        const val TEXT_MEASUREMENT_CHAR = "1"
    }

}