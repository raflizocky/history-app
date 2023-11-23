package com.zocky.historyofindonesia.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zocky.historyofindonesia.R
import com.zocky.historyofindonesia.data.History
import com.zocky.historyofindonesia.ui.detail.DetailActivity

class ListHistoryAdapter(private val listHistory: ArrayList<History>) :
    RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemCLickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemCLickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_history, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listHistory[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .into(holder.imgPhoto)
        holder.tvName.text = name
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_history", listHistory[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: History)
    }

    override fun getItemCount(): Int = listHistory.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
    }

}