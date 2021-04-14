package com.assignment.infosys.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.infosys.R
import com.assignment.infosys.data.Row
import com.assignment.infosys.databinding.ItemLayoutBinding
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsListAdapter (var partItemList: List<Row>, var mContext:Context, private val clickListener: (Row) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var context:Context = mContext

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemLayoutBinding.inflate(inflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as NewsViewHolder).bind(partItemList[position],context, clickListener)
    }

    override fun getItemCount() = partItemList.size

     class NewsViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(rowListData: Row,context:Context, clickListener: (Row) -> Unit) {

            binding.txtDescription.text = rowListData.description
            binding.textViewTitle.text = rowListData.title

            Glide.with(context)
                .load(rowListData.imageHref)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.imgProfilePic)

            binding.root.setOnClickListener { clickListener(rowListData) }
        }
    }
}