package com.hsmnzaydn.satellites.utils.fragment_controller

import androidx.fragment.app.Fragment



class FragmentOption(
    val fragment: Fragment,
    val label: String,
    val clearHistory: Boolean,
    val history: Boolean,
    val popupFragmentTag: String?,
    var tabMenuFragment: Boolean,
    var groupId: Int,
    var firstTabFragment: Boolean,
    var reCreate: Boolean
) {

    private constructor(builder: Builder) : this(
        builder.fragment, builder.label,
        builder.clearHistory,
        builder.history,
        builder.popupFragmentTag,
        builder.tabMenuFragment,
        builder.groupId,
        builder.firstTabFragment,
        builder.reCreate
    )

    companion object {
        inline fun build(
            fragment: Fragment,
            block: Builder.() -> Unit
        ) = Builder(fragment).apply(block).build()
    }

    class Builder(val fragment: Fragment) {

        var label: String = ""
        var clearHistory: Boolean = false
        var history: Boolean = true
        var popupFragmentTag: String? = null
        var tabMenuFragment: Boolean = false
        var groupId: Int = 0
        var firstTabFragment: Boolean = false
        var reCreate: Boolean = false

        fun build() = FragmentOption(this)
    }
}