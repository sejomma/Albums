package com.jommaa.albums

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
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
    fun assert_can_scroll_to_into_albums_list() {
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.recycler_albumsList)))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
    }

    @Test
    fun perform_album_item_click() {
        Thread.sleep(1000)
        Espresso.onView(AllOf.allOf(ViewMatchers.withId(R.id.recycler_albumsList)))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    1,
                    ViewActions.click()
                )
            )
    }
}