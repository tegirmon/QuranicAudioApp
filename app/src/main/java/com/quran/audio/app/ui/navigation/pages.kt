package com.quran.audio.app.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.quran.audio.app.R
import com.quran.audio.app.ui.data.MainViewModel
import com.quran.audio.app.ui.component.SectionTabs
import com.quran.audio.app.ui.component.SuraList

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Home(actions: MainActions, viewModel: MainViewModel) {
    Column {
        SectionTabs(actions, viewModel)
        Spacer(modifier = Modifier.requiredHeight(8.dp))
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SuraView(actions: MainActions, viewModel: MainViewModel) {
    Column {
        SuraList(actions, viewModel)
        Spacer(modifier = Modifier.requiredHeight(8.dp))
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.home, Icons.Filled.Home)
    object SuraList : Screen("suraList", R.string.sura_list, Icons.Filled.List)
    object Player : Screen("player", R.string.player, Icons.Filled.PlayArrow)
}
