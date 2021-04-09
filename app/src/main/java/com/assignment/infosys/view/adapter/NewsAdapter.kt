package com.assignment.infosys.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.assignment.infosys.R
import com.assignment.infosys.data.Row
import com.assignment.infosys.network.PicassoClient
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsAdapter(private var rowListData: List<Row>) :
RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {
     lateinit var mContext:Context
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        mContext = parent.context
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val textViewTitle = holder.itemView.findViewById(R.id.textViewTitle) as TextView
        val textViewDesc = holder.itemView.findViewById(R.id.txtDescription) as TextView
        val imgProfile  = holder.itemView.findViewById(R.id.imgProfilePic) as ImageView
        val imgRightArrows  = holder.itemView.findViewById(R.id.imgArrowRight) as ImageView
        textViewDesc.text = rowListData[position].description
        textViewTitle.text = rowListData[position].title

       /* Glide.with(mContext)
            .load(rowListData[position].imageHref)
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.itemView.imgProfilePic)*/

        //IMAGE
        PicassoClient.downloadImage(mContext,rowListData[position].imageHref,holder.itemView.imgProfilePic)

    }
    override fun getItemCount(): Int {
        return rowListData.size
    }


    //the class is hodling the list view
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       /* fun updateWithData(rowList: Row) {
            val textViewtitle = itemView.findViewById(R.id.txtDescription) as TextView
            val textViewDesc = itemView.findViewById(R.id.txtDescription) as TextView
            val imgProfile  = itemView.findViewById(R.id.imgProfilePic) as ImageView
            val imgRightArrows  = itemView.findViewById(R.id.imgArrowRight) as ImageView
            textViewDesc.text = rowList.title+ ":" +rowList.description

            //Picasso.get().load(rowList.imageHref).into(imgProfile)
        }*/
    }

}