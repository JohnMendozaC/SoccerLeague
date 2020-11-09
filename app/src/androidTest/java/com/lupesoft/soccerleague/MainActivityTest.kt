package com.lupesoft.soccerleague


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.lupesoft.soccerleague.utilities.waitFor
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain


@HiltAndroidTest
class MainActivityTest {

    private val hiltRule = HiltAndroidRule(this)
    private val activityTestRule = ActivityTestRule(MainActivity::class.java, true, true)

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(activityTestRule)

    @Test
    @Throws(InterruptedException::class)
    fun clickItemTeam() {
        onView(isRoot()).perform(waitFor(3500))
        onView(withId(R.id.team_list))
            .check(matches(isDisplayed()))
        onView(withId(R.id.team_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )
        onView(withId(R.id.card_team))
            .check(matches(isDisplayed()))
    }

    @Test
    @Throws(InterruptedException::class)
    fun clickItemSearchLeague() {
        onView(withId(R.id.search_league))
            .check(matches(isDisplayed()))
        onView(isRoot()).perform(waitFor(3500))
        onView(withId(R.id.search_league)).perform(click())
        onData(allOf(`is`(instanceOf(String::class.java)), `is`("AFC Champions League"))).perform(click())
        onView(withId(R.id.search_league)).check(matches(withSpinnerText(containsString("AFC Champions League"))))
    }

}