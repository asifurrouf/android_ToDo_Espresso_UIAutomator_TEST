package com.example.todo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(AndroidJUnit4.class)
public class TodoFirst {

    private static final String MY_APP_PACKAGE
            = "com.example.todo";

    private static final int LAUNCH_TIMEOUT = 5000;

    private static final String TODO_IS = "UiAutomator";
    private static final String PASSWORD_IS = "124563asdf";

    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {

        mDevice = UiDevice.getInstance(getInstrumentation());

        mDevice.pressHome();

        final String launcherPackage = getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);

        Context context = getApplicationContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(MY_APP_PACKAGE);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);    // Clear out any previous instances
        context.startActivity(intent);

        mDevice.wait(Until.hasObject(By.pkg(MY_APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkAppPreconditions() {
        assertThat(mDevice, notNullValue());
    }

    @Test
    public void checkGoToAnotherActivityWithText() throws InterruptedException {
        // Type text and then press the button.
        mDevice.findObject(By.res(MY_APP_PACKAGE,"fab"))
                .click();
        Thread.sleep(3000);
        mDevice.findObject(By.res(MY_APP_PACKAGE, "edit_todo"))
                .setText(TODO_IS);
        mDevice.findObject(By.res(MY_APP_PACKAGE, "button_save"))
                .click();

        Thread.sleep(2000);

    }

    private String getLauncherPackageName() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        PackageManager pm = getApplicationContext().getPackageManager();
        ResolveInfo resolveInfo = pm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo.activityInfo.packageName;
    }

}
