package com.example.githubrepos.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepos.model.Repo
import com.example.githubrepos.model.User
import com.example.githubrepos.network.RetroInstance
import com.example.githubrepos.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    var userListLiveData: MutableLiveData<User> = MutableLiveData()
    var repoListLiveData: MutableLiveData<ArrayList<Repo>> = MutableLiveData()
    var isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun getUser(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoadingLiveData.postValue(true)
                val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.getUserDataFromApi(username)
                userListLiveData.postValue(response)
            } catch (error: Error) {
                Log.i("teste", error.toString())
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }

    fun getRepos(username: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoadingLiveData.postValue(true)
                val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.getReposDataFromApi(username)
                repoListLiveData.postValue(response)
            } catch (error: Error) {
                Log.i("teste", error.toString())
            } finally {
                isLoadingLiveData.postValue(false)
            }
        }
    }
}