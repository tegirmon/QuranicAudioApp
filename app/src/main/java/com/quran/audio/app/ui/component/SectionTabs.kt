package com.quran.audio.app.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.quran.audio.app.ui.data.MainViewModel
import com.quran.audio.app.ui.navigation.NavActions

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SectionTabs(
    actions: NavActions,
    mainViewModel: MainViewModel
) {
    val pagerState = rememberPagerState()
    HorizontalPager(
        mainViewModel.sectionList.size,
        contentPadding = PaddingValues(horizontal = 32.dp),
        state = pagerState
    ) { page ->
        val section = mainViewModel.sectionList[page]
        Column() {
            SectionHeader(section.name)
            ReciterList(actions, mainViewModel, section.id)
        }
    }
}

@Composable
fun SectionHeader(sectionName: String) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        backgroundColor = MaterialTheme.colors.primary,
    ) {
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
        ) {
            Row(
                Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W900
                        )
                    ) {
                        append(sectionName)
                    }
                })
            }
        }
    }
}

