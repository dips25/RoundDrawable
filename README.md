Add dependency
'com.github.dips25:RoundDrawable:1.0'

//Create ImageView
img = (ImageView) findViewById(R.id.img1);

//Create Bitmap
var bmp:Bitmap = BitmapFactory.decodeResource(resources,R.drawable.sample_img)

//Circular Image
 var rd:RoundedDrawable = RoundedDrawable(bmp,true,false)

//Circular Image with border
var rd1:RoundedDrawable = RoundedDrawable(bmp,true,true)

//Rounded Corner Image with 20 radius
var rd2:RoundedDrawable = RoundedDrawable(bmp,true,20,false)

//Rounded Corner Image with 20 radius and border
var rd4:RoundedDrawable = RoundedDrawable(bmp,true,20,true)

//Rounded Corner Image with only topleft and topright corners with 20 radius
var rd5:RoundedDrawable = RoundedDrawable(bmp,20,20,0,0,false)

//Rounded Corner Image with only topleft and topright corners with 20 radius and border
var rd6:RoundedDrawable = RoundedDrawable(bmp,20,20,0,0,true)

//set the drawable to the imageview
img.setImageDrawable(rd1)
