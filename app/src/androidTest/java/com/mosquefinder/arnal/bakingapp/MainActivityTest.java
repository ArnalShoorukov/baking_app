package com.mosquefinder.arnal.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by arnal on 6/7/17.
 */

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureStepsListShow() {

        try{
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Type text and then press the button.
        onView(withId(R.id.bake_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.steps_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.steps_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(withId(R.id.step_list)).check(matches(isDisplayed()));
        onView(withId(R.id.next_button)).check(matches(isDisplayed()));
        onView(withId(R.id.prev_button)).check(matches(isDisplayed()));

    }

}
