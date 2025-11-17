package com.csstudent.manager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.csstudent.manager.data.GithubRepository
import com.csstudent.manager.databinding.ItemRepositoryBinding

class RepositoryAdapter : ListAdapter<GithubRepository, RepositoryAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRepositoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: GithubRepository) {
            binding.tvRepoName.text = repo.name
            binding.tvRepoDescription.text = repo.description ?: "설명이 없습니다"
            binding.tvRepoDescription.isVisible = !repo.description.isNullOrEmpty()
            binding.tvLanguage.text = repo.language ?: ""
            binding.tvLanguage.isVisible = !repo.language.isNullOrEmpty()
            binding.tvStars.text = repo.stars.toString()
            binding.tvForks.text = "Forks: ${repo.forks}"
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<GithubRepository>() {
        override fun areItemsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: GithubRepository, newItem: GithubRepository): Boolean {
            return oldItem == newItem
        }
    }
}
