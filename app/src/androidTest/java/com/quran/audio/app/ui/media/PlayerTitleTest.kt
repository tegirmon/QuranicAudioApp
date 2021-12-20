package com.quran.audio.app.ui.media

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class PlayerTitleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPlayerTitle() {
        val testTitle = "Test 1"
        composeTestRule.setContent {
            PlayerTitle(title = testTitle)
        }

        composeTestRule.onNodeWithText(testTitle).assertIsDisplayed()
    }
}