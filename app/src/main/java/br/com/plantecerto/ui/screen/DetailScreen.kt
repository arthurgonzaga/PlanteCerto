package br.com.plantecerto.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.ui.components.ListBottomSheet
import br.com.plantecerto.ui.components.TabSelector
import br.com.plantecerto.ui.components.TopInfoLayout
import br.com.plantecerto.ui.theme.PlanteCertoTheme
import br.com.plantecerto.ui.theme.ThemeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState


@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    vm: ThemeViewModel = viewModel(),
    pagerState: PagerState
) {

    val tabTitles = listOf("Informações", "Pragas", "Previsões")

    Column(
        modifier = Modifier
            .background(
                color = vm.getPallete().background
            )
    ){
        TopInfoLayout()
        TabSelector(
            modifier = Modifier.absoluteOffset(y = (-50).dp),
            titles = tabTitles,
            pagerState = pagerState
        )
        HorizontalPager(
            modifier = Modifier.absoluteOffset(y = (-50).dp),
            count = tabTitles.size,
            userScrollEnabled = false,
            state = pagerState,
        ) { page ->
            Column(
                Modifier.fillMaxSize()
            ) {
                Text(
                    text = "$page",
                    color = vm.getPallete().onBackground
                )
            }
        }
    }
}