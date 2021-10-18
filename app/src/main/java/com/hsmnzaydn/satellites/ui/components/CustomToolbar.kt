package com.hsmnzaydn.satellites.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import com.hsmnzaydn.satellites.R
import com.hsmnzaydn.satellites.databinding.ToolbarBinding
import com.hsmnzaydn.satellites.utils.gone
import com.hsmnzaydn.satellites.utils.show

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
        binding.titleTextView.show()
        binding.linearLayout.gone()
        binding.titleTextView.setText(title)
    }

    fun showSearch(){
        binding.titleTextView.gone()
        binding.linearLayout.show()
        binding.titleTextView.setText(title)
    }

    enum class ToolbarTypes {
        TITLE,
        SEARCH
    }
}