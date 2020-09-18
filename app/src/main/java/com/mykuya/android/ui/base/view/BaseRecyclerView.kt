package com.mykuya.android.ui.base.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView<T> : RecyclerView.Adapter<BaseRecyclerView.BaseViewHolder<T>>() {

    protected val mItems : MutableList<T> by lazy { ArrayList<T>() }

    protected  var callback : ((T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {
        if(viewType == -1){
            val view = LayoutInflater.from(parent.context)
                    .inflate(getLayoutIdCustom(), parent, false)
            return getViewHolder(view)
        }

        val view = LayoutInflater.from(parent.context)
                .inflate(getLayoutId(), parent, false)
        return getViewHolder(view)
    }

    override fun getItemCount(): Int = mItems.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindView(mItems[position])
    }

    abstract fun getLayoutId() : Int
    abstract fun getLayoutIdCustom() : Int
    abstract fun getViewHolder(view : View) : BaseViewHolder<T>

    abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bindView(item : T)
    }

    //-----------------------------
    fun setItems(items : MutableList<T>){
        mItems.clear()
        mItems.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item : T, isFirst : Boolean = false){
        if(isFirst){
            mItems.add(0, item)
            notifyItemInserted(0)
        }else{
            mItems.add(item)
            notifyItemInserted(mItems.size - 1)
        }
    }

    fun setListener(callback : (T) -> Unit){
        this.callback = callback
    }
}