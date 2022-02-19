package com.example.githubrepos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepos.adapter.RepoListAdapter
import com.example.githubrepos.model.Repo
import com.example.githubrepos.model.User
import com.example.githubrepos.viewModel.MainActivityViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var repoListAdapter: RepoListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initButton()
        initCard()
        initLoading()
        initUserList()
        observeData()
        observeLoading()
    }

    private fun observeData() {
        val userObserver = Observer<User> { newData ->
            if(newData != null) {
                card.visibility = View.VISIBLE
                Picasso.get().load(newData.avatar_url).into(profilePicture)
                nameTextView.text = newData.name
                loginTextView.text = newData.login
            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT)
            }
        }

        val repoListObserver = Observer<ArrayList<Repo>> { newData ->
            if(newData != null) {
                repoListAdapter.setUpdatedData(newData)
            } else {
                Toast.makeText(this, "No data found", Toast.LENGTH_SHORT)
            }
        }

        viewModel.userListLiveData.observe(this, userObserver)
        viewModel.repoListLiveData.observe(this, repoListObserver)
    }

    private fun observeLoading() {
        val loadingStateObserver = Observer<Boolean> {
            button.isEnabled = !it
            button.isClickable = !it
            if(it) {
                loading.visibility = View.VISIBLE
            } else {
                loading.visibility = View.GONE
            }
        }

        viewModel.isLoadingLiveData.observe(this, loadingStateObserver)
    }

    private fun initCard() {
        card.visibility = View.GONE
    }

    private fun initLoading() {
        loading.visibility = View.GONE
    }

    private fun initButton() {
        button.setOnClickListener {
            viewModel.getUser(loginTextInput.text.toString())
            viewModel.getRepos(loginTextInput.text.toString())
            loginTextInput.clearFocus()
        }
    }

    private fun initUserList() {
        userListRecyclerView.layoutManager = LinearLayoutManager(this)
        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        userListRecyclerView.addItemDecoration(decoration)
        repoListAdapter = RepoListAdapter()
        userListRecyclerView.adapter = repoListAdapter
    }
}