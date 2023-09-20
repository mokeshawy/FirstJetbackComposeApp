package com.example.firstappcompose

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import com.example.firstappcompose.core.Const
import com.example.firstappcompose.gyms.gyms_list.domain.domain_model.GymsScreenState
import com.example.firstappcompose.gyms.gyms_list.presentation.GymList
import org.junit.Rule
import org.junit.Test

class GymsScreenTest {

    @get:Rule
    val testRule: ComposeContentTestRule = createComposeRule()

    @Test
    fun loadingState_isActive() {
        testRule.setContent {
            GymList(state = GymsScreenState(
                gyms = emptyList(),
                isLoading = true
            ), onFavoriteItemClicked = { _, _ -> }, onItemCLicked = {})
        }
        testRule.onNodeWithContentDescription(Const.GYM_LIST_LOADING).assertIsDisplayed()
    }

}