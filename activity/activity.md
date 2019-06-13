# Activity

## StartActivityForResult()

- Activity Start

``` Kotlin
    companion object {
        val REQUEST_ID = 999
    }
    // Start
    activity.startActivityForResult(i, REQUEST_ID)

    // Result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ID) {
            when (resultCode) {
                RESULT_REMOVE -> {
                }
                RESULT_PUT_ON -> {
                }
            }
        }
    }
```

- Activity Second

``` Kotlin
    setResult(RESULT_REMOVE)
    // or
    setResult(RESULT_REMOVE, Intent())
    finish()
```
