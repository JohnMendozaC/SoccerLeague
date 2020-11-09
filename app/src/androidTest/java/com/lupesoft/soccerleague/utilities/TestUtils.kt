package com.lupesoft.soccerleague.utilities

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import com.lupesoft.soccerleague.data.country.CountryVo
import com.lupesoft.soccerleague.data.league.LeagueVo
import org.hamcrest.Matcher

val testCountrys = arrayListOf(
    CountryVo(1, "Andorra"),
    CountryVo(2, "Spain"),
    CountryVo(3, "England")
)
val testCountry = testCountrys[0]

val testLeagues = arrayListOf(
    LeagueVo("4328", "English Premier League", "Soccer", "Premier League"),
    LeagueVo("4329", "English League Championship", "Soccer", "Championship"),
    LeagueVo("4330", "Scottish Premier League", "Soccer", "Scottish Premiership")
)
val testLeague = testLeagues[0]

fun waitFor(delay: Long): ViewAction {
    return object : ViewAction {
        override fun perform(uiController: UiController?, view: View?) {
            uiController?.loopMainThreadForAtLeast(delay)
        }

        override fun getConstraints(): Matcher<View> {
            return isRoot()
        }

        override fun getDescription(): String {
            return "wait for " + delay + "milliseconds"
        }
    }
}