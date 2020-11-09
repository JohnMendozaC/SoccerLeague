package com.lupesoft.soccerleague.adapters

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lupesoft.soccerleague.R
import com.lupesoft.soccerleague.data.country.CountryVo
import com.lupesoft.soccerleague.data.league.LeagueVo
import com.lupesoft.soccerleague.data.team.League
import com.lupesoft.soccerleague.data.team.Team


@BindingAdapter("isGone")
fun bindIsGone(view: View, isGone: Boolean) {
    view.visibility = if (isGone) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("setDataTeams")
fun setDataTeam(recycler: RecyclerView, data: List<Team>?) {
    data?.also {
        (recycler.adapter as TeamAdapter).submitList(it)
    }
}

@BindingAdapter("imageUrl")
fun loadImage(view: AppCompatImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(url)
            .override(200, 300)
            .into(view)
    }
}

@BindingAdapter("linkDirection")
fun SendUrl(view: TextView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.setOnClickListener {
            val urlSend = if (url.contains("http://")) url else "http://${url}"
            Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(urlSend) }.also {
                startActivity(view.context, it, null)
            }
        }
    }
}

@BindingAdapter("textNotNull")
fun textNotNull(view: TextView, text: String?) {
    view.text = if (text.isNullOrEmpty()) view.context.getString(R.string.na) else text
}


@BindingAdapter("setDataEvents")
fun setDataPost(recycler: RecyclerView, data: List<League>?) {
    data?.also {
        val listNew = it.filter { !it.name.isNullOrEmpty() }
        (recycler.adapter as LeagueAdapter).submitList(
            if (listNew.isNotEmpty()) {
                listNew
            } else {
                listOf(League(name = recycler.context.getString(R.string.na)))
            }
        )
    }
}

@BindingAdapter("setDataCountry")
fun setDataCountry(sp: Spinner, data: List<CountryVo>?) {
    data?.also {
        sp.apply {
            ArrayList<String>().apply {
                add(sp.context.getString(R.string.search_country))
                it.forEach { c -> add(c.name) }
            }.also {
                adapter =
                    ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, it)
            }
        }
    }
}

@BindingAdapter("setDataLeague")
fun setDataLeague(sp: Spinner, data: List<LeagueVo>?) {
    data?.also {
        sp.apply {
            ArrayList<String>().apply {
                add(sp.context.getString(R.string.search_league))
                it.forEach { c -> c.strLeague?.also { add(it) } }
            }.also {
                adapter =
                    ArrayAdapter(context, R.layout.support_simple_spinner_dropdown_item, it)
            }
        }
    }
}