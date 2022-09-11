package br.com.plantecerto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.ui.theme.getPallete
import br.com.plantecerto.ui.utils.LogCompositions
import br.com.plantecerto.ui.utils.NoRippleInteractionSource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

/**
 * Created by Arthur Gonzaga on 9/7/2022
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabSelector(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    titles: List<String>
) {
    val selectedIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    LogCompositions("TabSelector","")
    TabRow(
        modifier = modifier
            .height(55.dp)
            .padding(horizontal = 28.dp)
            .fillMaxWidth(),
        divider = {},
        backgroundColor = Color.Transparent,
        indicator = { TabIndicator(tabPositions = it, pagerState = pagerState) },
        selectedTabIndex = selectedIndex
    ) {
        titles.forEachIndexed { index, s ->
            val isSelected = selectedIndex == index

            Tab(
                selected = isSelected,
                interactionSource = NoRippleInteractionSource(),
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            ) {
                Text(
                    text = s,
                    style = TextStyle(
                        letterSpacing = 1.5.sp,
                        fontWeight = if(isSelected) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    ),
                    color = if(isSelected) {
                        getPallete().onBackground
                    } else {
                        getPallete().secondary
                    }
                )
            }
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabIndicator(
    tabPositions: List<TabPosition>,
    pagerState: PagerState
) {
    Box(
        Modifier
            .pagerTabIndicatorOffset(pagerState, tabPositions)
            .height(4.dp)
            .padding(horizontal = 42.dp)
            .offset(y = (-5).dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(
                color = getPallete().onBackground,
                shape = MaterialTheme.shapes.medium
            )
    )
}