package com.quran.audio.app.ui.section

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.quran.audio.app.ui.reciter.ReciterList
import com.quran.audio.app.ui.reciter.ReciterViewModel
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun SectionTabs(viewModel: SectionViewModel, reciterViewModel: ReciterViewModel) {
    LaunchedEffect(Unit, block = {
        viewModel.getSuraList()
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

        viewModel.sectionList.forEachIndexed { index, section ->
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
        viewModel.sectionList.size,
        contentPadding = PaddingValues(horizontal = 32.dp),
        state = pagerState
    ) { page ->
        val section = viewModel.sectionList[page]
        ReciterList(viewModel = reciterViewModel, section.id)
    }
}