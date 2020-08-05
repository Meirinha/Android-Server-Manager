package com.mssm.servermanagement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mssm.servermanagement.R
import com.mssm.servermanagement.db.Server
import kotlinx.android.synthetic.main.server_element_view.view.*

class ServerListAdapter(val onClick: (Server) -> Unit, val onShare : (Server) -> Unit) : RecyclerView.Adapter<ServerViewHolder>() {

    var serverList : List<Server> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServerViewHolder {
       return ServerViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.server_element_view, parent, false)
       )
    }

    override fun getItemCount(): Int {
        return serverList.size
    }

    override fun onBindViewHolder(holder: ServerViewHolder, position: Int) {
        holder.setName(serverList[position], onClick, onShare)
    }

    fun updateList(list : List<Server>){
        serverList = list
        notifyDataSetChanged()
    }

}

class ServerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    fun setName(server : Server, onClick : (Server) -> Unit, onShare: (Server) -> Unit){
        itemView.list_server_name.text = server.name
        itemView.list_server_name.setOnClickListener{
            onClick(server)
        }
        itemView.share.setOnClickListener{
            onShare(server)
        }
    }

}