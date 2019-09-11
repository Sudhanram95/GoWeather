package com.sudhan.goweather;

import android.support.test.rule.ActivityTestRule;

import com.sudhan.goweather.getpermission.view.PermissionActivity;
import com.sudhan.goweather.utils.CommonUtil;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class RemoveDecimalTest {
    String input, expectedOutput, output;
    @Rule
    public ActivityTestRule<PermissionActivity> mActivityTestRule = new ActivityTestRule<>(PermissionActivity.class);
    private PermissionActivity mActivity = null;

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
        input = "35.5";
        expectedOutput = "35";
    }

    @Test
    public void dayOfWeekTest() {
        output = CommonUtil.INSTANCE.removeDecimal(input);
        Assert.assertEquals(expectedOutput, output);
    }
}
