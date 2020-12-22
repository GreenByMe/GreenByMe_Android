package org.greenbyme.angelhack.utils

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import org.greenbyme.angelhack.ui.BaseAdapter

object DataBindingUtils {
    @JvmStatic
    @BindingAdapter("bind:items")
    fun <T> bindItems(recyclerView: RecyclerView, items: List<T>?) {
        recyclerView.adapter.let { baseAdapter ->
            if (baseAdapter is BaseAdapter<*>) {
                val adapter = baseAdapter as BaseAdapter<T>
                items?.let {
                    adapter.setItems(it)
                    Log.e("DataBindingUtils", "Recyclerview adapt")
                }
            } else {
                Log.e("DataBindingUtils", "Recyclerview adapter casting failed")
            }

        }
    }
}