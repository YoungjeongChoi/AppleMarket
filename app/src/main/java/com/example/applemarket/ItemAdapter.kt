package com.example.applemarket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.MainRecycleItemBinding
import java.text.DecimalFormat

class ItemAdapter(val items: MutableList<Items>) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)

    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainRecycleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("recycle", "onCreateHolder")
        //왜 한번에 여유분(?) 9개 다 안 만들어두고 딱 보이는 것만 일단 만들어두는 걸까 어차피 스크롤 내릴 때 만들텐데
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
//        return super.getItemId(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
        holder.itemImg.setImageResource(items[position].image)
        holder.itemTitle.text = items[position].title
        holder.itemLocation.text = items[position].loca
        holder.itemPrice.text = "${DecimalFormat("#,###").format(items[position].price)}원"
        holder.itemChat.text = items[position].chat.toString()
        holder.itemLike.text = items[position].like.toString()
        Log.d("recycle", "onBind $position")
    }

    inner class ViewHolder(val binding: MainRecycleItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val itemImg = binding.ivRecyclePic
        val itemTitle = binding.tvRecycleTitle
        val itemLocation = binding.tvRecycleLoca
        val itemPrice = binding.tvRecyclePrice
        val itemChat = binding.tvRecycleChat
        val itemLike = binding.tvRecycleLike
    }

//    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_recycle_item, parent, false)) {
//        val itemImg =
//    }


}
