
fun String?.isNullOrBlank(): Boolean =  
        this == null || this.isBlank()

/** Get a View's position on the screen */
fun View.getAxisOnScreen(): Point
{
    val location = IntArray(2).apply(::getLocationOnScreen)
    return Point(location[0],location[1])
}
fun View.getAxisInWindow(): Point
{
    val location = IntArray(2).apply(::getLocationInWindow)
    return Point(location[0],location[1])
}

/** Change the tint for ImageView */
fun ImageView.setTint(colorId:Int){
    DrawableCompat.setTint(drawable, ContextCompat.getColor(context, colorId))
}

/** Trim/Cut/Crop the transparent boarder of a Bitmap */
fun Bitmap.trimTransparent(): Bitmap {
    val imgHeight = height
    val imgWidth = width
    val smallX = 0
    val smallY = 0
    var left = imgWidth
    var right = imgWidth
    var top = imgHeight
    var bottom = imgHeight
    for (i in 0 until imgWidth) {
        for (j in 0 until imgHeight) {
            if (getPixel(i, j) != Color.TRANSPARENT) {
                if (i - smallX < left) {
                    left = i - smallX
                }
                if (imgWidth - i < right) {
                    right = imgWidth - i
                }
                if (j - smallY < top) {
                    top = j - smallY
                }
                if (imgHeight - j < bottom) {
                    bottom = imgHeight - j
                }
            }
        }
    }
    return Bitmap.createBitmap(this, left, top, imgWidth - left - right, imgHeight - top - bottom)
}