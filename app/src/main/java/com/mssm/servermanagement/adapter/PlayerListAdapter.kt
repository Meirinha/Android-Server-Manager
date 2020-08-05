package com.mssm.servermanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mssm.servermanagement.R
import com.mssm.servermanagement.network.Player
import kotlinx.android.synthetic.main.player_element_view.view.*

class PlayerListAdapter (val onClick: (Player) -> Unit) : RecyclerView.Adapter<PlayerViewHolder>(){

    var playerList : List<Player> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.player_element_view, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.setPlayerInfo(playerList[position], onClick)
    }

    fun updateList(list : List<Player>){
        playerList = list
        notifyDataSetChanged()
    }
}


class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun setPlayerInfo(player : Player, onClick : (Player)-> Unit){
     itemView.player_name.text = player.name
    }

}