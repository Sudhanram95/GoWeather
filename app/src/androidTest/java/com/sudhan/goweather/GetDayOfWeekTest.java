package com.sudhan.goweather;

import android.support.test.rule.ActivityTestRule;

import com.sudhan.goweather.getpermission.view.PermissionActivity;
import com.sudhan.goweather.utils.CommonUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class  GetDayOfWeekTest {
    String input, expectedOutput, output;
    @Rule
    public ActivityTestRule<PermissionActivity> mActivityTestRule = new ActivityTestRule<>(PermissionActivity.class);
    private PermissionActivity mActivity = null;

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
        input = "2019-03-06";
        expectedOutput = "Wednesday";
    }

    @Test
    public void dayOfWeekTest() {
        output = CommonUtil.INSTANCE.getDayOfWeek(input);
        Assert.assertEquals(expectedOutput, output);
    }
}
