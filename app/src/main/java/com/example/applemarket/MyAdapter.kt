package com.example.applemarket
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.applemarket.databinding.ActivityMainBinding
//
//class MyAdapter(val item: MutableList<Items>) : RecyclerView.Adapter<MyAdapter.Holder>() {
//
//    interface ItemClick {
//        fun onClick(view: View, position: Int)
//    }
//
//    var itemClick: ItemClick? = null
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.Holder {
//        val binding =
//            ActivityMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return Holder(binding)
//    }
//
//    override fun onBindViewHolder(holder: MyAdapter.Holder, position: Int) {
//        holder.itemView.setOnClickListener {
//            itemClick?.onClick(it, position)
//        }
//        holder.image.setImageResource(item[position].image)
//        holder.title.text = item[position].title
//        holder.seller.text = item[position].seller
//        holder.location.text = item[position].location
//        holder.context.text = item[position].context
//        holder.price.text = item[position].price
//        holder.like.text = item[position].like
//        holder.chat.text = item[position].chat
//    }
//
//    override fun getItemId(position: Int): Long {
//        return super.getItemId(position)
//    }
//
//    override fun getItemCount(): Int {
//        return item.size
//    }
//
//    inner class Holder(val binding: ActivityMainBinding) : RecyclerView.ViewHolder(binding.root) {
//        val image = binding
//        val title = binding
//        val seller = binding
//        val location = binding
//        val context = binding
//        val price = binding
//        val like = binding
//        val chat = binding
//    }
//
//}