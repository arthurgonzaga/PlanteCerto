package br.com.plantecerto.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.plantecerto.domain.data.model.Themes
import br.com.plantecerto.ui.components.PageInfo
import br.com.plantecerto.ui.components.TabSelector
import br.com.plantecerto.ui.components.TopInfoLayout
import br.com.plantecerto.ui.theme.getPallete
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    theme: Themes,
    pagerState: PagerState
) {
    val tabTitles = listOf("Informações", "Pragas", "Previsões")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = getPallete().background)
            .verticalScroll(rememberScrollState())
    ){
        TopInfoLayout()
        TabSelector(
            modifier = Modifier.absoluteOffset(y = (-50).dp),
            titles = tabTitles,
            pagerState = pagerState
        )
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize()
                .absoluteOffset(y = (-50).dp),
            count = tabTitles.size,
            state = pagerState,
        ) { page ->
            LazyColumn(
                modifier = Modifier.sizeIn(maxHeight = 50000.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
                items(
                    items = theme.pageData.data[page]!!
                ) { item ->
                    PageInfo(item)
                    Spacer(modifier = Modifier.height(16.dp))
                }
                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }

}