package com.example.rawgapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rawgapp.R
import com.example.rawgapp.data.entity.GameEntity

class GameAdapter(private val context: Context) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var mGameList: MutableList<GameEntity> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_game, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val gameEntity=mGameList[position]
        holder.tvGameName.text=gameEntity.name
    }

    override fun getItemCount()=mGameList.size

    fun updateGameList(gameList:List<GameEntity>){
        mGameList.clear()
        mGameList=gameList.toMutableList()
        notifyDataSetChanged()
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvGameName: TextView = itemView.findViewById(R.id.tvGameName)
    }
}