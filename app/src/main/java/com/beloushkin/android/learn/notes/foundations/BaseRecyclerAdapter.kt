package com.beloushkin.android.learn.notes.foundations

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerAdapter<T>(
    protected val masterList: MutableList<T> = mutableListOf()
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateList(list: List<T>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallbackImpl(masterList,list))
        masterList.clear()
        masterList.addAll(list)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int = if (position == 0) {
        TYPE_ADD_BUTTON
    } else {
        TYPE_INFO
    }

    override fun getItemCount(): Int = masterList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            (holder as BaseViewHolder<T>).onBind(masterList[position], position) // for button

    abstract class AddButtonViewHolder(view: View ) : BaseViewHolder<Unit>(view)

    abstract class BaseViewHolder<E>(val view: View): RecyclerView.ViewHolder(view) {
        abstract fun onBind(data: E, listIndex: Int)
    }

    companion object {
        const val TYPE_ADD_BUTTON = 0
        const val TYPE_INFO = 1
    }

    //DiffUtil for list changes
    class DiffUtilCallbackImpl<T>(
        val oldList: List<T>,
        val newList: List<T>
    ): DiffUtil.Callback() {
        // might be deeper comparison
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean=
            oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == newList[newItemPosition]
    }
}