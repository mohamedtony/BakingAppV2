package com.example.android.bakingapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakingapp.activity.MainActivity;
import com.example.android.bakingapp.activity.ReceipeDetailActivity;

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
public class RecipeDetailsActivityTest {

    @Rule
    public ActivityTestRule<ReceipeDetailActivity> activityActivityTestRule =
            new ActivityTestRule<>(ReceipeDetailActivity.class, true, false);

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkTextViews() {

        Espresso.registerIdlingResources(mActivityTestRule.getActivity().getIdlingResource());

        onView(withId(R.id.reycler_recepies))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        onView(allOf(withId(R.id.tv_ingredients), withText("Ingredients"), isDisplayed()));
        onView(allOf(withId(R.id.tv_steps), withText("Steps"), isDisplayed()));

        onView(withId(R.id.recipes_detail_recycler)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        //2. perform Action on  the view
        onView(withId(R.id.next)).perform(click());
        //3. check if the view done with your expected
        onView(withId(R.id.next)).perform(click());
        onView(withId(R.id.step_instruction)).check(matches(withText("2. Melt the butter and bittersweet chocolate together in a microwave or a double boiler. If microwaving, heat for 30 seconds at a time, removing bowl and stirring ingredients in between.")));

        onView(withId(R.id.prev)).perform(click());
        onView(withId(R.id.prev)).perform(click());
        onView(withId(R.id.step_instruction)).check(matches(withText("Recipe Introduction")));



    }


/*    @Test
    public void checkRecyclerViews() {
        onView(allOf(withId(R.id.ingredients_list), isDisplayed()));
        onView(allOf(withId(R.id.steps_list), isDisplayed()));
    }*/

}
