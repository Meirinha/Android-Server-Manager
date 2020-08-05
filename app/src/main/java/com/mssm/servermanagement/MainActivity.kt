package com.mssm.servermanagement

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.navigation.ui.AppBarConfiguration
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mssm.servermanagement.adapter.ServerListAdapter
import com.mssm.servermanagement.db.Server
import com.mssm.servermanagement.fragment.ServerDetailsFragment
import com.mssm.servermanagement.fragment.ServerDialogFragment
import com.mssm.servermanagement.general.hide
import com.mssm.servermanagement.general.show
import com.mssm.servermanagement.viewmodel.ServerListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var viewModel : ServerListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(ServerListViewModel::class.java)

        setContentView(R.layout.activity_main)

        serverPrepareList()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        list_add_server.setOnClickListener { addServer() }
        add_server.setOnClickListener { addServer() }

    }

    private fun onClickServer(server : Server){

        val frag = ServerDetailsFragment.newInstance(server)
        supportFragmentManager.beginTransaction().replace(R.id.main_space, frag).commitNow()

    }

    private fun onClickShare(server : Server){

        val sendIntent : Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, server.URL)
            type = "text/plain"
        }
         val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)

    }

    private fun serverPrepareList() {

        list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = ServerListAdapter(this::onClickServer, this::onClickShare)
        list.adapter = adapter

        viewModel.serverList.observe(this, Observer {
            if(it.isEmpty()){
                no_server_text.show()
                add_server.show()
            }
            else{
                no_server_text.hide()
                add_server.hide()
            }
            adapter.updateList(it)
        })
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    private fun addServer() {
        ServerDialogFragment().show(supportFragmentManager, "server_settings")
    }
}