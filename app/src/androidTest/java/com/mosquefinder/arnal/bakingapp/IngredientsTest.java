package com.mosquefinder.arnal.bakingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.contrib.RecyclerViewActions;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;

/**
 * Created by arnal on 6/6/17.
 */

public class IngredientsTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule
            = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void RecipeList_Change_Show_Ingredient_Step_Lable(){
        try{
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.bake_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ingredients)).check(matches(isDisplayed()));
        onView(withId(R.id.steps)).check(matches(isDisplayed()));
    }
}
