package com.assignment.infosys.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.assignment.infosys.R
import com.assignment.infosys.data.Row
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsAdapter(var newsListData: List<Row>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     var rowListData: List<Row> = newsListData
    lateinit var mContext:Context

    fun setAdapterListData(listData: List<Row>){
            this.rowListData = listData
        notifyDataSetChanged()
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        mContext = parent.context
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val textViewTitle = holder.itemView.findViewById(R.id.textViewTitle) as TextView
        val textViewDesc = holder.itemView.findViewById(R.id.txtDescription) as TextView
        textViewDesc.text = rowListData[position].description
        textViewTitle.text = rowListData[position].title

        Glide.with(mContext)
            .load(rowListData[position].imageHref)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.itemView.imgProfilePic)

    }
    override fun getItemCount(): Int {
        return rowListData.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}