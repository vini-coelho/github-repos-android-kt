package com.example.githubrepos.model

data class UserList (val items: ArrayList<User>)
data class User (val name: String, val login: String, val avatar_url: String)
data class Repo (val name: String, val full_name: String, val description: String, val topics: ArrayList<String>)