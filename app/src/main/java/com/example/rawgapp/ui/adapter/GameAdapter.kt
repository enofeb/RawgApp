package com.example.rawgapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.rawgapp.R
import com.example.rawgapp.data.local.entity.GameEntity
import kotlinx.android.synthetic.main.item_layout_game.view.*

class GameAdapter(private val context: Context,private val listener:(Int)->Unit) :
    PagedListAdapter<GameEntity,GameAdapter.GameViewHolder>(GameDiffCallback()) {

    private var mGameList: MutableList<GameEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_game, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        //  val gameEntity = mGameList[position]

        (holder as GameViewHolder).bind(getItem(position))

        holder.itemView.setOnClickListener {
            listener(getItem(position)!!.gameId)
        }
     //   holder.tvGameName.text = gameEntity.name

      //  holder.itemView.setOnClickListener {
      //      listener(gameEntity.gameId)
       // }
    }

    override fun getItemCount() = super.getItemCount()

    fun updateGameList(gameList: List<GameEntity>) {
        mGameList.clear()
        mGameList = gameList.toMutableList()
        notifyDataSetChanged()
    }

    class GameDiffCallback:DiffUtil.ItemCallback<GameEntity>(){
        override fun areItemsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
           return oldItem.gameId==newItem.gameId
        }

        override fun areContentsTheSame(oldItem: GameEntity, newItem: GameEntity): Boolean {
            return oldItem==newItem
        }
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(gameEntity: GameEntity?){
            itemView.tvGameName.text=gameEntity?.name
        }

        val tvGameName: TextView = itemView.findViewById(R.id.tvGameName)
    }
}