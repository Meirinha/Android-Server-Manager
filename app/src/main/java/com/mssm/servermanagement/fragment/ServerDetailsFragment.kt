package com.mssm.servermanagement.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mssm.servermanagement.R
import com.mssm.servermanagement.adapter.PlayerListAdapter
import com.mssm.servermanagement.db.Server
import com.mssm.servermanagement.general.hide
import com.mssm.servermanagement.general.show
import com.mssm.servermanagement.network.Player
import com.mssm.servermanagement.viewmodel.ServerDetailsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_server_info.*

class ServerDetailsFragment : Fragment() {

    companion object {

        const val KEY = "info"


        fun newInstance(server: Server): ServerDetailsFragment {
            val fragment = ServerDetailsFragment()
            fragment.arguments = Bundle()
            fragment.arguments?.putParcelable(KEY, server)
            return fragment
        }
    }

    private lateinit var viewModel: ServerDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServerDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_server_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val server = arguments?.getParcelable<Server>(KEY)

        if (server == null) {
            Toast.makeText(context, R.string.missing_server_error, Toast.LENGTH_SHORT).show()
        } else {
            server_name_list.text = server.name
            viewModel.setServer(server)
            playerPrepareList()
            prepareButtons()
        }


        viewModel.isOnline.observe(viewLifecycleOwner, Observer {
            status.background = resources.getDrawable(
                if (it) {
                    start_server.text = getString(R.string.stop)
                    R.drawable.server_status_up
                } else {
                    start_server.text = getString(R.string.start)
                    R.drawable.server_status_down
                }
            )
        }
        )

    }

    private fun prepareButtons() {

        start_server.setOnClickListener {
            viewModel.startStop()
        }

        update_server.setOnClickListener {
            viewModel.updateServer()
        }

        delete_server.setOnClickListener {
            AlertDialog.Builder(requireContext()).setMessage(R.string.are_u_sure).setNegativeButton(R.string.no) { _,_ -> }
                .setPositiveButton(R.string.yes) { _,_ -> viewModel.deleteServer()}.show()
        }
    }

    private fun playerPrepareList() {

        player_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter = PlayerListAdapter(this::onClick)
        player_list.adapter = adapter

        viewModel.playerList.observe(viewLifecycleOwner, Observer {
            adapter.updateList(it)
        })
    }

    private fun onClick(player: Player) {

        viewModel.kickPlayer(player)

    }
}