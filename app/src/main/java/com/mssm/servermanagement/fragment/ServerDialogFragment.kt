package com.mssm.servermanagement.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.mssm.servermanagement.R
import com.mssm.servermanagement.db.Server
import com.mssm.servermanagement.general.Validations
import com.mssm.servermanagement.viewmodel.ServerViewModel
import kotlinx.android.synthetic.main.dialog_server_settings.*

class ServerDialogFragment: DialogFragment() {

    private lateinit var viewModel : ServerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ServerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_server_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        save_button.setOnClickListener {

            saveServer()

        }
    }

    private fun saveServer() {

        val url = server_url_input.text.toString()
        val name = server_name_input.text.toString()
        val username = server_username_input.text.toString()
        val password = server_password_input.text.toString()

        if(Validations.validateURL(url) && Validations.validateText(name, username, password)){
            viewModel.saveServer(Server(url, name, username, password))
            dismiss()
        }
        else{
            Toast.makeText(context, R.string.server_settings_error, Toast.LENGTH_LONG).show()
        }

    }

}