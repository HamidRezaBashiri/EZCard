package com.hamidrezabashiri.ezcard

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hamidrezabashiri.ezcard.ui.screens.MainActivity
import com.hamidrezabashiri.ezcard.ui.screens.login.LoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testLoginSuccess() {
        // Launch the login screen
        composeTestRule.setContent {
            LoginScreen(onLoginSuccess = {}, viewModel = loginViewModel)
        }

//        // Enter a valid password in the password field
//        onView(withId(R.id.passwordTextField))
//            .perform(replaceText("myPassword"), closeSoftKeyboard())
//
//        // Click the login button
//        onView(withId(R.id.loginButton)).perform(click())
//
//        // Check that the onLoginSuccess callback was called
//        // This assumes that the LoginScreen has a parameter called onLoginSuccess that is called when login is successful
//        onView(isRoot()).perform(waitFor(2000))
//        // waitFor is an extension function from the androidx.test.espresso.extensions package
//        // It is used to wait for a certain amount of time before continuing with the test
//        // This is useful when waiting for a UI transition or animation to complete before continuing with the test
//        onView(withId(R.id.loginSuccessMessage)).check(matches(isDisplayed()))
    }

    @Test
    fun testLoginFailure() {
        // Launch the login screen
        composeTestRule.setContent {
            LoginScreen(onLoginSuccess = {}, viewModel = loginViewModel)
        }

//        // Enter an invalid password in the password field
//        onView(withId(R.id.passwordTextField))
//            .perform(replaceText("wrongPassword"), closeSoftKeyboard())
//
//        // Click the login button
//        onView(withId(R.id.loginButton)).perform(click())
//
//        // Check that the onLoginSuccess callback was not called
//        onView(isRoot()).perform(waitFor(2000))
//        // This assumes that the LoginScreen has a parameter called onLoginSuccess that is called when login is successful
//        // In this case, the onLoginSuccess callback should not be called because the password is invalid
//        onView(withId(R.id.loginSuccessMessage)).check(doesNotExist())
    }
}
