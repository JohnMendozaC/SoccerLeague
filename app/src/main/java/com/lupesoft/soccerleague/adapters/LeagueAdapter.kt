package com.lupesoft.soccerleague.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lupesoft.soccerleague.data.team.League
import com.lupesoft.soccerleague.databinding.ItemEventBinding

class LeagueAdapter : ListAdapter<League, RecyclerView.ViewHolder>(LeagueDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return LeagueViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val league = getItem(position)
        (holder as LeagueViewHolder).bind(league)
    }

    class LeagueViewHolder(
        private val binding: ItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: League) {
            binding.apply {
                league = item
                executePendingBindings()
            }
        }
    }
}

private class LeagueDiffCallback : DiffUtil.ItemCallback<League>() {
    override fun areItemsTheSame(oldItem: League, newItem: League): Boolean =
        oldItem.idLeague == newItem.idLeague

    override fun areContentsTheSame(oldItem: League, newItem: League): Boolean =
        oldItem == newItem
}