package com.quran.audio.app.ui.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.quran.audio.app.ui.data.MainViewModel

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun MainNavGraph(
    navController: NavHostController,
    actions: NavActions,
    mainViewModel: MainViewModel
) {
    LaunchedEffect(Unit, block = {
        mainViewModel.getSectionList()
        mainViewModel.getReciterList()
        mainViewModel.getSuraList()
    })
    NavHost(navController, Screen.Home.route) {
        composable(Screen.Home.route) {
            Home(actions, mainViewModel)
        }
        composable(Screen.SuraList.route) {
            SuraView(mainViewModel)
        }
        composable(Screen.PlayList.route) {
            PlayListView(mainViewModel)
        }
    }
}