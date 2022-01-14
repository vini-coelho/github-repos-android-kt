package com.example.githubrepos.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.model.User
import com.example.githubrepos.model.UserList
import com.example.githubrepos.network.RetroInstance
import com.example.githubrepos.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    var userListLiveData: MutableLiveData<User> = MutableLiveData()

    fun getUserListObserver(): MutableLiveData<User> {
        return userListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("vini-coelho")
            Log.i("response", response.toString())
        }
    }
}