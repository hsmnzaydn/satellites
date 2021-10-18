package com.hsmnzaydn.satellites.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.ColorRes
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

fun View.changeBackgroundTint(context: Context, @ColorRes colorId: Int) {
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        this.backgroundTintList =
            ContextCompat.getColorStateList(context, colorId)
    }
}

fun EditText.keyboardFocus() {
    this.requestFocus()
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

fun EditText.hideSoftKeyboard() {
    this.clearFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}