/**
 * This example only moves the Y direction
 */
    
    var y0 = 0f

    someView.setOnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                y0 = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                var d = v.translationY + event.rawY - y0
                // coerceIn some range or not
                d = d.coerceIn((v.basicLine.top - v.height + 35).toFloat(), 0f)
                v.translationY = d
                y0 = event.rawY
            }
            MotionEvent.ACTION_UP -> {
                if (!skip)
                    scrollToBound(v, (root.basicLine.top - v.height + 35).toFloat(), 0f)
            }
        }
        true
    }

/**
 * Optional function to detect quick move to do something.
 */

    private fun detectQuickMove(speed: Float, v: View): Boolean =
    when {
        speed > 100 -> {
            Log.d("PDF", "detectQuickMove > 100")
            v.animate().translationY(0f).setInterpolator(DecelerateInterpolator()).duration = 300
            true
        }
        speed < -100 -> {
            Log.d("PDF", "detectQuickMove < -100")
            v.animate().translationY((v.basicLine.top - v.height + 35).toFloat())
                .setInterpolator(DecelerateInterpolator()).duration = 300
            true
        }
        else -> false
    }
