# Glide usage

## Integrate with `RecyclerView`

To use the `RecyclerView` integration library you need to follow a couple of steps:

1. Create a `PreloadSizeProvider`
2. Create a `PreloadModelProvider`
3. Create the `RecyclerViewPreloader` given the `PreloadSizeProvider` and `PreloadModelProviders` you created in the first two steps
4. Add your `RecyclerViewPreloader` to your `RecyclerView` as a scroll listener.
5. Each of these steps is outlined in more detail below.

## Crop Image to Circle

```kt
Glide.with(context).load(uri).apply(RequestOptions().circleCrop()).into(imageView)
```

More

```kt
 Glide.with(context!!)
    .load(randomImage)
    .apply(RequestOptions.bitmapTransform(CircleCrop()).error(R.drawable.nyancat_animated))
    .transition(DrawableTransitionOptions()
            .crossFade())
    .into(picture)
```

## Override CustomTarget to do something with `Bitmap`

```kt
abstract class GlideSimpleBitmapTarget: CustomTarget<Bitmap>() {
    var mRequest :Request? = null
    override fun onLoadStarted(placeholder: Drawable?) {

    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
    }

    override fun onStop() {
    }

    override fun onLoadCleared(placeholder: Drawable?) {
    }

    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
    }

    override fun onStart() {
    }

    override fun onDestroy() {
    }
}
```