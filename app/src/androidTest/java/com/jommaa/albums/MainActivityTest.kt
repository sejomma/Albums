package com.jommaa.albums

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.jommaa.albums.view.activities.MainActivity
import org.hamcrest.core.AllOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setup() {

    }

    @Test
    fun assert_loading_appears() {
        Espresso.onView((ViewMatchers.withId(R.id.progress))).check(ViewAssertions.matches(
            ViewMatchers.isDisplayed()))
    }

    @Test
    fun assert_can_scroll_to_into_albums_list() {
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.recycler_albumsList)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }
}