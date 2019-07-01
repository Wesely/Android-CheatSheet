# Draggable CardView

A Drawer from bottom, can be dragged.

- Will auto align to Top / Bottom based on the position.
- Will auto align to Top / Bottom based on the scrolling speed.

## Code

### OnTouchListener

```kt
    private fun renderView(root: View) {
        var y0 = 0f
        var skip = false // a flag to skip all the resting actions
        root.cvDetail.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    y0 = event.rawY
                    skip = false
                }
                MotionEvent.ACTION_MOVE -> {
                    if(skip)
                        return@setOnTouchListener false
                    var d = v.translationY + event.rawY - y0
                    d = d.coerceIn((v.basicLine.top - v.height + 35).toFloat(), 0f)
                    v.translationY = d
                // if speed is fast, skip all the rest.
                    skip = detectQuickMove(event.rawY - y0, v)
                    Log.d("PDF-ACTION_MOVE", "speed:${event.rawY - y0}")
                    y0 = event.rawY
                }
                MotionEvent.ACTION_UP -> {
                    if (!skip)
                        scrollToBound(v, (root.basicLine.top - v.height + 35).toFloat(), 0f)
                }
            }
            true
        }
    }
```

### Detect Quick Move

If scrolling speed, i.e. delta Y, is large enough: scroll to bound, and return a flag to skip all the resting actions.

```kt
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
```

### Detect Position When `MOTION_UP`

If `MOTION_UP`, set the `View` to one of it's destinition

```kt
    private fun scrollToBound(view: View, min: Float, max: Float) {
        val mid = (min + max) / 2
        if (view.translationY > mid) {
            view.animate().translationY(max).setInterpolator(DecelerateInterpolator()).duration = 300
        } else {
            view.animate().translationY(min).setInterpolator(DecelerateInterpolator()).duration = 300
        }
    }
```
