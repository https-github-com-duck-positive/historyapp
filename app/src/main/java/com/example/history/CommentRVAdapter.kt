package com.example.history


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.history.databinding.ItemCommentBinding
import com.example.history.databinding.ItemImgBinding


class CommentRVAdapter(private var commentList : ArrayList<String>) : RecyclerView.Adapter<CommentRVAdapter.ViewHolder>(){


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCommentBinding
            .inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder : ViewHolder, position : Int){
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size
    inner class ViewHolder(val binding : ItemCommentBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(comment : String){
            binding.itemCommentCommentTv.text = comment
        }
    }

}