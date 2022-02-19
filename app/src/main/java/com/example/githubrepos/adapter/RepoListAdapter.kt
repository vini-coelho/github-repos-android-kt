package com.example.githubrepos.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepos.R
import com.example.githubrepos.model.Repo
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.repo_list_row.*

class RepoListAdapter: RecyclerView.Adapter<RepoListAdapter.MyViewHolder>() {
    var items = ArrayList<Repo>()

    fun setUpdatedData(items: ArrayList<Repo>) {
        this.items = items
        notifyDataSetChanged()
    }

    class MyViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(data: Repo) {
            repo_name.text = data.name
            repo_description.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_list_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}