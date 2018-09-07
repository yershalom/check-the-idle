package com.yershalom.checktheidle

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import org.junit.Rule
import org.junit.Test


class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun waitForCategories() {
        onView(withText("gadgets")).check(matches(withText("gadgets")))
    }

    @Test
    fun clickOnCategory() {
        onView(withText("gadgets")).perform(click())
    }

    @Test
    fun clickAnything() {
        onView(withId(R.id.rv_topic))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(11))
    }
}