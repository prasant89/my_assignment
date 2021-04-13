package com.assignment.infosys.view.ui

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.assignment.infosys.R
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
   public var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(
        MainActivity::class.java,
        true,
        true
    )

    @Test
    fun onCreate_test() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewNews))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(activityRule.activity.window.decorView)
            )).check(ViewAssertions.matches(isDisplayed()))
    }

}