package com.zireck.remotecraft.view.activity;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.azimolabs.conditionwatcher.ConditionWatcher;
import com.zireck.remotecraft.R;
import com.zireck.remotecraft.espresso.action.FabMenuCloseAction;
import com.zireck.remotecraft.espresso.action.FabMenuOpenAction;
import com.zireck.remotecraft.espresso.instruction.FabMenuClosedInstruction;
import com.zireck.remotecraft.espresso.instruction.FabMenuOpenInstruction;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class) public class SearchServerActivityInstrumentationTest {

  @Rule public ActivityTestRule<SearchServerActivity> activityTestRule =
      new ActivityTestRule<>(SearchServerActivity.class);

  @Test public void shouldProperlyOpenTheFabMenu() throws Exception {
    onView(withId(R.id.menu)).perform(new FabMenuOpenAction());
    ConditionWatcher.waitForCondition(new FabMenuOpenInstruction());

    onView(withId(R.id.fab_qrcode)).check(matches(isDisplayed()));
  }

  @Test public void shouldProperlyCloseTheFabMenu() throws Exception {
    onView(withId(R.id.menu)).perform(new FabMenuCloseAction());
    ConditionWatcher.waitForCondition(new FabMenuClosedInstruction());

    onView(withId(R.id.fab_qrcode)).check(matches(not(isDisplayed())));
  }

  @Test public void shouldProperlyOpenAndCloseTheFabMenu() throws Exception {
    onView(withId(R.id.menu)).perform(new FabMenuOpenAction());
    ConditionWatcher.waitForCondition(new FabMenuOpenInstruction());
    onView(withId(R.id.fab_qrcode)).check(matches(isDisplayed()));


    onView(withId(R.id.menu)).perform(new FabMenuCloseAction());
    ConditionWatcher.waitForCondition(new FabMenuClosedInstruction());
    onView(withId(R.id.fab_qrcode)).check(matches(not(isDisplayed())));
  }

  @Test public void shouldDisplayEnterAddressDialog() throws Exception {
    onView(withId(R.id.menu)).perform(new FabMenuOpenAction());
    ConditionWatcher.waitForCondition(new FabMenuOpenInstruction());
    onView(withId(R.id.fab_ip)).check(matches(isDisplayed()));
    onView(withId(R.id.fab_ip)).perform(click());

    onView(withText(R.string.enter_network_address_dialog_button_accept)).check(
        matches(isDisplayed()));
  }

  @Test public void shouldDismissEnterAddressDialogWhenCancel() throws Exception {
    onView(withId(R.id.menu)).perform(new FabMenuOpenAction());
    ConditionWatcher.waitForCondition(new FabMenuOpenInstruction());
    onView(withId(R.id.fab_ip)).check(matches(isDisplayed())).perform(click());
    onView(withText(R.string.enter_network_address_dialog_button_accept)).check(
        matches(isDisplayed()));
    onView(withText(R.string.enter_network_address_dialog_button_cancel)).check(
        matches(isDisplayed())).perform(click());

    onView(withText(R.string.enter_network_address_dialog_button_accept)).check(doesNotExist());
  }
}