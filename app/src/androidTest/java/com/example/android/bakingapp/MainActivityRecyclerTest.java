package com.example.android.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by medo on 14-Nov-17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityRecyclerTest {



        @Rule
        public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


        @Test
        public void checkText_RecipeActivity() {
              //  onView(withId(R.id.reycler_recepies)).perform(RecyclerViewActions.scrollToPosition(3));
                // onView(withText("Nutella Pie")).check(matches(isDisplayed()));
               // onView(withId(R.id.tv_recipe)).check(matches(withText("Brownies")));
            //onView(withId(R.id.reycler_recepies)).check(ViewAssertions.matches(isDisplayed()));
/*
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            Espresso.registerIdlingResources(mActivityTestRule.getActivity().getIdlingResource());



            //-====================================== is all displayed =======================================
            onView(allOf(ViewMatchers.withId(R.id.reycler_recepies), isDisplayed()));


            //-====================================== for text=======================================
            onView(withId(R.id.reycler_recepies)).perform(RecyclerViewActions.scrollToPosition(0));
            onView(withText("Brownies")).check(matches(isDisplayed()));


            //============================================== on item clicked ===================================
            onView(withId(R.id.reycler_recepies))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

            //-============================================= for the detail activity recycler view ====================
            onView(withId(R.id.recipes_detail_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));


        }
    }

