package com.csstudent.manager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.csstudent.manager.data.Term
import com.csstudent.manager.databinding.ItemTermBinding

class TermAdapter(
    private val onDeleteClick: (Term) -> Unit
) : ListAdapter<Term, TermAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTermBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding, onDeleteClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemTermBinding,
        private val onDeleteClick: (Term) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(term: Term) {
            binding.tvTerm.text = term.term
            binding.tvCategory.text = term.category
            binding.tvDefinition.text = term.definition

            binding.btnDelete.setOnClickListener {
                onDeleteClick(term)
            }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<Term>() {
        override fun areItemsTheSame(oldItem: Term, newItem: Term): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Term, newItem: Term): Boolean {
            return oldItem == newItem
        }
    }
}
