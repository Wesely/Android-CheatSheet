# RecyclerView

## RecyclerView Itself

```Kotlin
recyclerView.layoutManager = LinearLayoutManager(this)
recyclerView.layoutManager = GridLayoutManager(this, 2)
recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
recyclerView.adapter = adapter
```

## Adapter with ViewHolder

Just create a class `MyAdapter` extends `RecyclerView.Adapter<MyViewHolder>()`

and `MyViewHolder` within it.

``` Kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(val context: Context,val data:List<Any>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myView1 = itemView.myView
        val myView2 = itemView.myView2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // inflate the layout here,
        val itemView = (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) 
                as LayoutInflater)
            .inflate(R.layout.my_item, parent,false)

        /**if viewType is used:*/
        // when(viewType){
        //
        // }
        return MyViewHolder(itemView)
    }

    /**if view type is used:*/
    //override fun getItemViewType(position: Int): Int {
    //    return super.getItemViewType(position)
    //}

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        Glide.with(context).asBitmap().load("imageUrl").into(holder.myView1)
        holder.myView2.text = "some string"
    }
}
```

## OnItemClickedListener()

1. Create a Interface

    ```kt
    interface ItemClickListener {
        fun onItemClick(view: View, int: Int)
    }
    ```

2. Implement the Interface in the `Activity`/`Fragment`

3. Passing events through the interface

    ```kt
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        // ...
        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(holder.itemView, position)
        }
    }
    ```

4. `adapter?.onItemClickListener = this`
