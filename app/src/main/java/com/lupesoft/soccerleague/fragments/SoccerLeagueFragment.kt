package com.lupesoft.soccerleague.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lupesoft.soccerleague.adapters.TeamAdapter
import com.lupesoft.soccerleague.api.Api.Companion.SPAIN
import com.lupesoft.soccerleague.api.Api.Companion.SPORT
import com.lupesoft.soccerleague.data.Status
import com.lupesoft.soccerleague.databinding.FragmentSoccerLeagueLayoutBinding
import com.lupesoft.soccerleague.viewmodels.SoccerLeagueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SoccerLeagueFragment : Fragment() {

    private val viewModel: SoccerLeagueViewModel by viewModels()
    private lateinit var binding: FragmentSoccerLeagueLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSoccerLeagueLayoutBinding.inflate(inflater, container, false)
        context ?: return binding.root
        binding.teamList.adapter = TeamAdapter()
        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {
        binding.loader.isLoading = false
        viewModel.countrys.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when (result.status) {
                    Status.LOADING -> loader.isLoading = false
                    Status.SUCCESS -> {
                        listCountry = result.data
                        loader.isLoading = true
                    }
                    Status.ERROR -> {
                        listCountry = null
                        loader.isLoading = true
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                initSearchCountry()
            }
        }

        viewModel.leagues.observe(viewLifecycleOwner) { result ->
            binding.apply {
                when (result.status) {
                    Status.LOADING -> loader.isLoading = false
                    Status.SUCCESS -> {
                        listLeague = result.data
                    }
                    Status.ERROR -> {
                        listLeague = null
                        Toast.makeText(context, "Error", Toast.LENGTH_LONG)
                            .show()
                    }
                }
                initSearchLeague()
            }
        }
    }

    private fun initSearchCountry() {
        binding.searchCountry.apply {
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        id: Long
                    ) {
                        binding.apply {
                            val country = (position - 1).let {
                                if (it > 0)
                                    listCountry?.get(it)?.name ?: SPAIN
                                else
                                    SPAIN
                            }
                            searchTeams(country = country, s = SPORT)
                        }
                    }
                }

        }
    }

    private fun initSearchLeague() {
        binding.searchLeague.apply {
            onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        position: Int,
                        id: Long
                    ) {
                        binding.apply {
                            (position - 1).let {
                                if (it > 0)
                                    searchTeams(league = listLeague?.get(it)?.strLeague ?: "")
                            }
                        }
                    }
                }

        }
    }

    private fun searchTeams(country: String? = null, s: String? = null, league: String? = null) {
        viewModel.teams(country = country, s = s, league = league)
            .observe(viewLifecycleOwner) { result ->
                binding.apply {
                    when (result.status) {
                        Status.LOADING -> loader.isLoading = false
                        Status.SUCCESS -> {
                            listTeam = result.data
                            loader.isLoading = true
                        }
                        Status.ERROR -> {
                            listTeam = null
                            loader.isLoading = true
                            Toast.makeText(
                                context,
                                "Error",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                }
            }
    }

}