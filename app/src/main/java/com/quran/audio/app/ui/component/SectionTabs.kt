package com.quran.audio.app.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.quran.audio.app.ui.data.MainViewModel
import com.quran.audio.app.ui.navigation.MainActions
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SectionTabs(
    actions: MainActions,
    mainViewModel: MainViewModel
) {
    LaunchedEffect(Unit, block = {
        mainViewModel.getSectionList()
    })
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        mainViewModel.sectionList.forEachIndexed { index, section ->
            Tab(
                text = { Text(section.name) },
                selected = pagerState.currentPage == index,
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }

    HorizontalPager(
        mainViewModel.sectionList.size,
        contentPadding = PaddingValues(horizontal = 32.dp),
        state = pagerState
    ) { page ->
        val section = mainViewModel.sectionList[page]
        ReciterList(actions, mainViewModel, section.id)
    }
}