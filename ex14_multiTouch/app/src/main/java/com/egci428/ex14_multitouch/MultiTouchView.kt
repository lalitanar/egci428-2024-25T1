package com.egci428.ex14_multitouch

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View

/*
* Created by Lalita N. on 29/10/24
*/

class MultiTouchView(context: Context): View(context) {

    private var mActivePointers: SparseArray<PointF>? = null
    private var mPaint: Paint? = null
    private val colors = intArrayOf(Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.LTGRAY, Color.YELLOW, Color.DKGRAY)
    private var textPaint: Paint? = null

    init {
        mActivePointers = SparseArray<PointF>()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.color = Color.BLACK
        mPaint!!.style = Paint.Style.FILL_AND_STROKE

        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint!!.textSize = 30F
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val pointerIndex = event!!.actionIndex
        val pointerId = event.getPointerId(pointerIndex)
        val maskedAction = event.actionMasked

        when(maskedAction) {
            MotionEvent.ACTION_DOWN,MotionEvent.ACTION_POINTER_DOWN ->{
                val f = PointF()
                f.x = event.getX(pointerIndex)
                f.y = event.getY(pointerIndex)
                mActivePointers!!.put(pointerId, f)
            }
            MotionEvent.ACTION_MOVE -> {
                val size = event.pointerCount
                var i = 0
                while (i < size) {
                    val point = mActivePointers!![event.getPointerId(i)]
                    if(point != null) {
                        point.x = event.getX(i)
                        point.y = event.getY(i)
                    }
                    i++
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP, MotionEvent.ACTION_CANCEL -> {
                mActivePointers!!.remove(pointerId)
            }
        }
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val size = mActivePointers!!.size()
        var  i = 0

        while (i < size) {
            val point = mActivePointers!!.valueAt(i)
            if (point != null) {
                mPaint!!.color = colors[i%10]
                canvas.drawCircle(point.x, point.y, 60F, mPaint!!)
                i++
            }
        }
        canvas!!.drawText("Total pointer: "+size, 10F, 80F, textPaint!!)
    }

}





