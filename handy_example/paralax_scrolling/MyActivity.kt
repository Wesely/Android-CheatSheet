
/**
    * Set ImageView's scroll by current position.
    * @direction : 1=comes from right, -1 = Left
    */
private fun setScrollOfImage(cv: View?, v: ImageView, direction: Int = 1) {
    // Screen's params
    val min = 0
    val max = screenH
    val center = (screenH / 2).toFloat()
    // image's alpha will be 1.0 while passing `show`
    val show = max - 800f

    val location = IntArray(2)
    v.getLocationOnScreen(location)
    // position
    val p = location[1]
    // transparency
    val alpha =
        when {
            p < show -> 1f
            p > max -> 0f
            else -> ((p - max) / (show - max)).coerceIn(0f, 1f)
        }
    cv?.let {
        cv.alpha = alpha
        // translationX
        val x = 400 * (1 - alpha) * direction
        cv.translationX = x
    }
    // Image Scroll
    v.scrollY = ((p - center) * 400 / max).toInt()

}