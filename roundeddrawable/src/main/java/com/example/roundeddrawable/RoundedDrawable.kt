package com.example.roundeddrawable

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.BlurMaskFilter
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.MaskFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.graphics.drawable.Drawable


class RoundedDrawable: Drawable {

    var bitmap:Bitmap?=null
    var width:Int?=0
    var height:Int?=0
    var matrix:Matrix?=null
    var paint:Paint?=null
    var radius:Int=0
    var cX:Float = 0f;
    var cY:Float = 0f;
    var isCircle:Boolean?=false
    var allCorners:Boolean?=false

    var topLeft:Int=0
    var topRight:Int=0
    var bottomLeft:Int=0
    var bottomRight:Int=0

    var mainRect:RectF?=null

    var isBorder:Boolean?=false

    var borderPaint:Paint?=null

    var destRect:RectF?=null


    constructor(bmp: Bitmap,isCircle:Boolean,isBorder:Boolean):super(){

        this.bitmap = bmp
        this.isCircle = isCircle
        this.isBorder = isBorder

        if (isBorder) {

            borderPaint = Paint()
            borderPaint!!.isAntiAlias = true
            borderPaint!!.color = Color.BLACK
            borderPaint!!.style = Paint.Style.FILL_AND_STROKE
        }

        bitmap?.let {

            width = bitmap!!.width
            height = bitmap!!.height
            matrix = Matrix()
            paint = Paint(Paint.ANTI_ALIAS_FLAG)

        }


    }

    constructor(bmp: Bitmap,allCorners:Boolean,radius:Int,isBorder:Boolean):super(){

        this.bitmap = bmp
        this.allCorners = allCorners
        this.radius = radius
        this.isBorder = isBorder

        if (isBorder) {

            borderPaint = Paint()
            borderPaint!!.isAntiAlias = true
            borderPaint!!.color = Color.BLACK
            borderPaint!!.style = Paint.Style.FILL_AND_STROKE
        }

        bitmap?.let {

            width = bitmap!!.width
            height = bitmap!!.height
            matrix = Matrix()
            paint = Paint(Paint.ANTI_ALIAS_FLAG)

        }


    }

    constructor(bmp: Bitmap,topLeft:Int,topRight:Int,bottomLeft:Int,bottomRight:Int,isBorder:Boolean):super(){

        this.bitmap = bmp
        this.topLeft = topLeft
        this.topRight = topRight
        this.bottomLeft = bottomLeft
        this.bottomRight = bottomRight
        this.isBorder = isBorder

        if (isBorder) {

            borderPaint = Paint()
            borderPaint!!.isAntiAlias = true
            borderPaint!!.color = Color.BLACK
            borderPaint!!.style = Paint.Style.FILL_AND_STROKE
        }



        bitmap?.let {

            width = bitmap!!.width
            height = bitmap!!.height
            matrix = Matrix()
            paint = Paint(Paint.ANTI_ALIAS_FLAG)

        }


    }

    override fun draw(canvas: Canvas) {

        if (bitmap != null) {

            var path: Path = Path()
            var borderPath = Path()


            if (isCircle!!) {

                if (isBorder!!) {


                    borderPath.addCircle(cX, cY, radius.toFloat(), Path.Direction.CW)

                    path.addCircle(cX, cY, radius.toFloat() - 5f, Path.Direction.CW)


                } else {

                    path.addCircle(cX, cY, radius.toFloat(), Path.Direction.CW)

                }


            } else if (allCorners!!) {

                val radii = floatArrayOf(
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat(),
                    radius.toFloat()
                )

                if (isBorder!!) {

                    borderPath.addRoundRect(
                        0f,
                        0f,
                        mainRect!!.right,
                        mainRect!!.bottom,
                        radii,
                        Path.Direction.CW
                    )
                    path.addRoundRect(
                        destRect!!.left,
                        destRect!!.top,
                        destRect!!.right,
                        destRect!!.bottom,
                        radii,
                        Path.Direction.CW
                    )


                } else {

                    path.addRoundRect(
                        0f,
                        0f,
                        mainRect!!.right,
                        mainRect!!.bottom,
                        radii,
                        Path.Direction.CW
                    )


                }


            } else {

                val radii = floatArrayOf(
                    topLeft.toFloat(),
                    topLeft.toFloat(),
                    topRight.toFloat(),
                    topRight.toFloat(),
                    bottomLeft.toFloat(),
                    bottomLeft.toFloat(),
                    bottomRight.toFloat(),
                    bottomRight.toFloat()
                )


                if (isBorder!!) {

                    borderPath.addRoundRect(
                        0f,
                        0f,
                        mainRect!!.right,
                        mainRect!!.bottom,
                        radii,
                        Path.Direction.CW
                    )
                    path.addRoundRect(
                        destRect!!.left,
                        destRect!!.top,
                        destRect!!.right,
                        destRect!!.bottom,
                        radii,
                        Path.Direction.CW
                    )


                } else {

                    path.addRoundRect(
                        0f,
                        0f,
                        mainRect!!.right,
                        mainRect!!.bottom,
                        radii,
                        Path.Direction.CW
                    )


                }


            }

            if (isBorder!!) {

                canvas.drawPath(borderPath, borderPaint!!)
                canvas.clipPath(borderPath)

            }



            canvas.drawPath(path, paint!!)
            canvas.clipPath(path)

        } else {

            throw RuntimeException("Bitmap is null")
        }
    }

    override fun setAlpha(alpha: Int) {


    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }

    override fun getOpacity(): Int {

        return PixelFormat.OPAQUE
    }

    override fun onBoundsChange(bounds: Rect) {
        super.onBoundsChange(bounds)

        if (bitmap != null) {

            var srcRect: RectF = RectF(0f, 0f, width!!.toFloat()!!, height!!.toFloat())
            destRect = RectF(bounds)

            mainRect = RectF(bounds)

            if (isBorder!!) {

                destRect!!.inset(5f, 5f)
            }

            matrix!!.setRectToRect(srcRect, destRect, Matrix.ScaleToFit.FILL)

            var shader = BitmapShader(bitmap!!, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
            shader.setLocalMatrix(matrix)

            paint!!.setShader(shader)


            matrix!!.mapRect(srcRect)

            if (isCircle!!) {

                cX = destRect!!.width() / 2
                cY = destRect!!.height() / 2

                radius = (srcRect.width() / 2).toInt()


            }


        }
    }


}