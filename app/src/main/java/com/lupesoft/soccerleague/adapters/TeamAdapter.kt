package com.lupesoft.soccerleague.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lupesoft.soccerleague.R
import com.lupesoft.soccerleague.data.team.Team
import com.lupesoft.soccerleague.databinding.ItemTeamBinding

class TeamAdapter : ListAdapter<Team, RecyclerView.ViewHolder>(TeamDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TeamViewHolder(
            ItemTeamBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val team = getItem(position)
        (holder as TeamViewHolder).bind(team)
    }

    class TeamViewHolder(
        private val binding: ItemTeamBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.team?.let { data ->
                    navigate(data, it)
                }
            }
        }

        private fun navigate(elem: Team, view: View) {
            view.findNavController().navigate(
                R.id.action_soccerLeagueFragment_to_teamDetailsFragment, bundleOf(
                    "team" to elem
                )
            )
        }

        fun bind(item: Team) {
            binding.apply {
                team = item
                executePendingBindings()
            }
        }
    }
}

private class TeamDiffCallback : DiffUtil.ItemCallback<Team>() {
    override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean =
        oldItem.idTeam == newItem.idTeam

    override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean =
        oldItem == newItem
}