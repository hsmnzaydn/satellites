package com.hsmnzaydn.satellites.utils

import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

@JvmOverloads
fun <A : RecyclerView.Adapter<*>> A.vertical(
    recyclerView: RecyclerView,
    dividerItemDecoration: Int? = DividerItemDecoration.VERTICAL,
    @DrawableRes separatorDrawable: Int? = null
): A {
    recyclerView.run {
        this.layoutManager =
            layoutManager ?: LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = this@vertical
        separatorDrawable?.let {
            addItemDecoration(
                DividerItemDecoration(context, dividerItemDecoration!!).apply {
                    setDrawable(
                        ContextCompat.getDrawable(context, it)!!
                    )
                }
            )
        }
    }
    return this
}