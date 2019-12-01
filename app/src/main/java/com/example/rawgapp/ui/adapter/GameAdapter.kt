package com.example.rawgapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rawgapp.R
import com.example.rawgapp.data.local.entity.GameEntity
import com.example.rawgapp.databinding.ItemLayoutGameBinding

class GameAdapter(private val listener:(Int)->Unit) :
    PagedListAdapter<GameEntity,GameAdapter.GameViewHolder>(GameDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_game, parent, false)
        val inflater = LayoutInflater.from(parent.context)
        val binding=ItemLayoutGameBinding.inflate(inflater)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {

        holder.bind(getItem(position))

        holder.itemView.setOnClickListener {
            listener(getItem(position)!!.gameId)
        }
    }

    override fun getItemCount() = super.getItemCount()

    class GameDiffCallback:DiffUtil.ItemCallback<GameEntity>(){
        override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
           return oldItem.gameId==newItem.gameId
        }

        override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
            return oldItem==newItem
        }
    }

    class GameViewHolder(val binding: ItemLayoutGameBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(gameEntity: GameEntity?){
            binding.game=gameEntity
            binding.executePendingBindings()
       }
    }
}