package edu.gatech.seclass.wordfind6300;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;
import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
//import android.support.test.annotation.UiThreadTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

//import edu.gatech.seclass.wordfind6300.database.GameStatsDao;
//import edu.gatech.seclass.wordfind6300.database.StatsDatabase;
//import edu.gatech.seclass.wordfind6300.database.StatsViewModel;


import androidx.test.espresso.Espresso;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityTestRule<GameSession> ActivityRule = new ActivityTestRule<>(
    GameSession.class, true, false);
    GameSession myGameSession = null;

    @Before
    public void init(){
        Intent intent = new Intent();
        intent.putExtra("boardSizeSetting", "4");
        intent.putExtra("wtSetting", "1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1");
        intent.putExtra("timerSetting", "3");

        ActivityRule.launchActivity(intent);
        myGameSession = ActivityRule.getActivity();
    }

    @Test
    public void testEnterWord(){
        onView(allOf(withTagValue(is((Object) 1)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 9)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 15)), isDisplayed())).perform(click());
        onView(withId(R.id.CurrentWord)).check(matches(withText(myGameSession.wordEntered)));

    }

    @Test
    public void testEnterScore() {

        onView(allOf(withTagValue(is((Object) 0)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 1)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 2)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 3)), isDisplayed())).perform(click());
        int expectScore = myGameSession.wordEntered.length();
        onView(withId(R.id.ValidateButton)).perform(click());
        assertEquals(myGameSession.finalScore, expectScore);
    }

    @Test
    public void testEmptyWords() {
        onView(withId(R.id.ValidateButton)).perform(click());
        onView(withText("The word length is less than 2."))
                .inRoot(withDecorView(not(myGameSession.getWindow()
                        .getDecorView()))).check(matches(isDisplayed()));
    }
    @Test
    public void testShortWords() {
        onView(allOf(withTagValue(is((Object) 0)), isDisplayed())).perform(click());
        onView(withId(R.id.ValidateButton)).perform(click());
        onView(withText("The word length is less than 2."))
                .inRoot(withDecorView(not(myGameSession.getWindow()
                        .getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testWordNotConnect(){
        onView(allOf(withTagValue(is((Object) 0)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 9)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 15)), isDisplayed())).perform(click());
        onView(withId(R.id.ValidateButton)).perform(click());
        onView(withText("The letter entered is not connected with previous one!"))
                .inRoot(withDecorView(not(myGameSession.getWindow()
                        .getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testWordDuplicateLetter(){
        onView(allOf(withTagValue(is((Object) 0)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 0)), isDisplayed())).perform(click());
        onView(withId(R.id.ValidateButton)).perform(click());
        onView(withText("The letter is already entered!"))
                .inRoot(withDecorView(not(myGameSession.getWindow()
                        .getDecorView()))).check(matches(isDisplayed()));
    }

    @Test
    public void testWordDuplicateWord(){
        onView(allOf(withTagValue(is((Object) 0)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 1)), isDisplayed())).perform(click());
        onView(withId(R.id.ValidateButton)).perform(click());
        onView(allOf(withTagValue(is((Object) 0)), isDisplayed())).perform(click());
        onView(allOf(withTagValue(is((Object) 1)), isDisplayed())).perform(click());
        onView(withId(R.id.ValidateButton)).perform(click());
        onView(withText("This word has been entered."))
                .inRoot(withDecorView(not(myGameSession.getWindow()
                        .getDecorView()))).check(matches(isDisplayed()));
    }

}
