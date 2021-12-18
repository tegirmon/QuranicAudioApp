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
import com.quran.audio.app.ui.reciter.ReciterViewModel
import com.quran.audio.app.ui.section.SectionTabs
import com.quran.audio.app.ui.section.SectionViewModel
import com.quran.audio.app.ui.sura.SuraList
import com.quran.audio.app.ui.sura.SuraViewModel

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Home(actions: MainActions, sectionViewModel: SectionViewModel, reciterViewModel: ReciterViewModel) {
    Column {
        SectionTabs(actions, sectionViewModel, reciterViewModel)
        Spacer(modifier = Modifier.requiredHeight(8.dp))
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SuraView(actions: MainActions, suraViewModel: SuraViewModel, reciterRelativePath: String?) {
    Column {
        SuraList(actions, suraViewModel, reciterRelativePath)
        Spacer(modifier = Modifier.requiredHeight(8.dp))
    }
}

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.home, Icons.Filled.Home)
    object SuraList : Screen("suraList", R.string.sura_list, Icons.Filled.List)
    object Player : Screen("player", R.string.player, Icons.Filled.PlayArrow)
}
