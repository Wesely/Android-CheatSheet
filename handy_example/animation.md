# Animations

## Bounce (Sine)

- Bounce up and down based on sine wave.
- Decays through time.

```kt
    Handler().post(object : Runnable {
        override fun run() {
            val t = System.currentTimeMillis() - startTime
            val d = sin(t * 2 * Math.PI / 500) * 50 * (1000-t)/1000
            root.cvDetail.translationY = d.toFloat()
            if (t < 1000) {
                root.postDelayed(this, 20)
            } else {
                root.cvDetail.translationY = 0f
            }
        }
    })
```
