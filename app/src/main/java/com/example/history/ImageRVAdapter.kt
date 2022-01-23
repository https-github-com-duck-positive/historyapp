package com.example.history


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemImgBinding


class ImageRVAdapter(private val imageList : List<Image>) : RecyclerView.Adapter<ImageRVAdapter.ViewHolder>(){

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImgBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int){
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
    inner class ViewHolder(val binding : ItemImgBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(image : Image){
            binding.writeImgIv?.setImageBitmap(image.image)
        }
    }

}