package com.example.githubrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.githubrepos.model.User
import com.example.githubrepos.viewModel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeData()
        initButton()
    }

    private fun observeData() {
        val userListObserver = Observer<User> { newData ->
            Log.i("observer", newData.toString())
        }

        viewModel.userListLiveData.observe(this, userListObserver)
    }

    private fun initButton() {
        button.setOnClickListener {
            viewModel.makeApiCall()
        }
    }
}