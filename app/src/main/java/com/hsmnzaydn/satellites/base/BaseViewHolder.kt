package com.hsmnzaydn.satellites.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.hsmnzaydn.satellites.base.model.BaseEntity
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<M : BaseEntity> : RecyclerView.ViewHolder, LayoutContainer {

    var onViewClick: ((M, View) -> Unit) = { _, _ -> }

    constructor(parent: ViewGroup, @LayoutRes layoutId: Int) : this(
        LayoutInflater.from(parent.context).inflate(
            layoutId,
            parent,
            false
        )
    )

    constructor(itemView: View) : super(itemView)

    override val containerView: View?
        get() = this.itemView

    /**
     * ViewHolder içerisinde contexte ihtiyac duyulduğunda bu değişken kullanılabilir
     * */
    val context: Context
        get() = this.itemView.context

    abstract fun bindItem(item: M)

    internal fun bindItem(item: M, onItemClick: (M,position:Int,layoutId:Int) -> Unit) {
        with(itemView) {
            setOnClickListener { onItemClick(item,adapterPosition,item.layoutId) }
            bindItem(item)
        }
    }

    internal fun setOnViewClick(onViewClick: ((M, View) -> Unit)): BaseViewHolder<M> {
        this.onViewClick = onViewClick
        return this
    }
}

