package com.sudhan.goweather;

import android.os.Build;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sudhan.goweather.getpermission.view.PermissionActivity;
import com.sudhan.goweather.utils.CommonUtil;
import com.sudhan.goweather.utils.LocationUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UITest {
    private PermissionActivity mActivity = null;

    @Rule
    public ActivityTestRule<PermissionActivity> mainActivityTestRule =
            new ActivityTestRule<>(PermissionActivity.class);

    @Before
    public void setUp() {
        mActivity = mainActivityTestRule.getActivity();
    }

    @Test
    public void testUIFlow() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(!CommonUtil.INSTANCE.isLocationPermissionGranted(mActivity)) {
                //Disable the location permission and select DENY on the requestPermission popup
                onView(withId(R.id.rel_location_dialog)).check(matches(isDisplayed()));
                onView(withId(R.id.btn_grand_permission)).perform(click());
                onView(withId(R.id.rel_activity_permission)).check(matches(isDisplayed()));
            } else {
                whenPermissionEnabled();
            }
        } else {
            whenPermissionEnabled();
        }
    }

    public void whenPermissionEnabled() {
        try {
            //Enable location permission and turn on your GPS
            onView(withId(R.id.ll_forecast)).check(matches(isDisplayed()));
        } catch (Exception e) {
            //Enable location permission and turn off your GPS
            onView(withId(R.id.rel_activity_permission)).check(matches(isDisplayed()));
            onView(withId(R.id.edt_city)).perform(clearText(), typeText("Chennai"));
            onView(withId(R.id.edt_city)).perform(closeSoftKeyboard());
            onView(withId(R.id.rel_next)).perform(click());
            onView(withId(R.id.ll_forecast)).check(matches(isDisplayed()));
        }
    }
}
