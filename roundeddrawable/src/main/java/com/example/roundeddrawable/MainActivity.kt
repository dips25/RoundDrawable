package com.example.roundeddrawable

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var img:ImageView = findViewById(R.id.img)
        var img1:ImageView = findViewById(R.id.img1)
        var img2:ImageView = findViewById(R.id.img2)
        var img4:ImageView = findViewById(R.id.img4)
        var img5:ImageView = findViewById(R.id.img5)
        var img6:ImageView = findViewById(R.id.img6)


        var bmp:Bitmap = BitmapFactory.decodeResource(resources,R.drawable.sample_img)

        //var rd:RoundedDrawable = RoundedDrawable(bmp,20,20,0,0,true)

        //var rd:RoundedDrawable = RoundedDrawable(bmp,true,20,true)

        var rd:RoundedDrawable = RoundedDrawable(bmp,true,false)
        var rd1:RoundedDrawable = RoundedDrawable(bmp,true,true)

        var rd2:RoundedDrawable = RoundedDrawable(bmp,true,20,false)
        var rd4:RoundedDrawable = RoundedDrawable(bmp,true,20,true)

        var rd5:RoundedDrawable = RoundedDrawable(bmp,20,20,0,0,false)
        var rd6:RoundedDrawable = RoundedDrawable(bmp,20,20,0,0,true)





        img.setImageDrawable(rd)
        img1.setImageDrawable(rd1)

        img2.setImageDrawable(rd2)
        img4.setImageDrawable(rd4)

        img5.setImageDrawable(rd5)
        img6.setImageDrawable(rd6)



    }
}