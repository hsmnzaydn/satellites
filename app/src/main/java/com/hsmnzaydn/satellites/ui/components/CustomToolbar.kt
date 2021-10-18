package com.hsmnzaydn.satellites.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.hsmnzaydn.satellites.R
import com.hsmnzaydn.satellites.databinding.ToolbarBinding
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemService
import android.app.Activity

import android.widget.EditText
import com.hsmnzaydn.satellites.utils.*


class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs) {

    private val binding: ToolbarBinding = ToolbarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var title = ""


    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CustomToolbar)


        attributes.apply {
            title = getString(R.styleable.CustomToolbar_android_text) ?: ""
            setType(ToolbarTypes.values()[getInt(R.styleable.CustomToolbar_toolbar_type, 0)])
            with(binding) {
                titleTextView.setText(title)

            }

            recycle()
        }

        clickListners()

    }

    private fun clickListners() {
        binding.appCompatImageView.setOnClickListener {

            if(binding.linearLayout.isVisible){
                showSearch()
                binding.searchEditText.hideSoftKeyboard()
            }else{
                binding.linearLayout.show()
                binding.titleTextView.gone()
                binding.appCompatImageView.setImageResource(R.drawable.ic_close)
                binding.searchEditText.show()
                binding.searchEditText.keyboardFocus()
            }

        }
    }



    fun setType(toolbarTypes: ToolbarTypes) {
        when (toolbarTypes) {
            ToolbarTypes.TITLE -> {
                showTitle()
            }
            ToolbarTypes.SEARCH -> {
              showSearch()
            }
            else -> {
                showTitle()
            }
        }
    }

    fun setTitle(title:String){
        binding.titleTextView.setText(title)
    }

    fun getSearchInput(callback:(input:String) -> Unit){
        binding.searchEditText.addTextChangedListener {
            callback.invoke(it.toString())
        }
    }

    fun showTitle(){
        binding.appCompatImageView.gone()
        binding.titleTextView.show()
        binding.linearLayout.gone()
        binding.titleTextView.setText(title)
    }

    fun showSearch(){
        binding.titleTextView.show()
        binding.appCompatImageView.show()
        binding.linearLayout.gone()
        binding.titleTextView.setText(title)
        binding.appCompatImageView.setImageResource(R.drawable.ic_search)
        binding.appCompatImageView.changeBackgroundTint(context,R.color.white)
    }

    enum class ToolbarTypes {
        TITLE,
        SEARCH
    }
}