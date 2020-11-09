package com.lupesoft.soccerleague.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lupesoft.soccerleague.adapters.LeagueAdapter
import com.lupesoft.soccerleague.data.team.Team
import com.lupesoft.soccerleague.databinding.FragmentTeamDetailsLayoutBinding

class TeamDetailsFragment : Fragment() {

    private lateinit var binding: FragmentTeamDetailsLayoutBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeamDetailsLayoutBinding.inflate(inflater, container, false)
        container ?: return binding.root
        binding.apply {
            listEvent.adapter = LeagueAdapter()
            team = (arguments?.get("team") as Team)
        }
        return binding.root
    }

}