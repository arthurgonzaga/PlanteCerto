package br.com.plantecerto.ui.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.domain.data.entity.model.Info
import br.com.plantecerto.domain.data.entity.model.Themes
import br.com.plantecerto.ui.components.PageInfo
import br.com.plantecerto.ui.components.TabSelector
import br.com.plantecerto.ui.components.TopInfoLayout
import br.com.plantecerto.ui.screen.detail.DetailViewModel.Companion.GENERIC_ERROR
import br.com.plantecerto.ui.theme.LocalTheme
import br.com.plantecerto.ui.theme.getPallete
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState


private const val INFORMACOES = 0
private const val PRAGAS = 1
private const val PREVISOES = 2

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailScreen(
    theme: Themes,
    pagerState: PagerState,
    vm: DetailViewModel = hiltViewModel()
) {
    val tabTitles = listOf("Informações", "Pragas", "Previsões")

    val state by vm.uiState

    LaunchedEffect(pagerState.currentPage, theme) {
        if(pagerState.currentPage == 2) vm.getPredictions(theme.pageData.title)
    }

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
            when(page) {
                INFORMACOES, PRAGAS-> {
                    DetailList(items = theme.pageData.data[page])
                }
                PREVISOES -> {
                    when {
                        state.isLoading -> {
                            PrevisaoLoading()
                        }
                        state.hasError -> {
                            PrevisaoErro(
                                errorMessage = state.error!!.message
                            )
                        }
                        else -> {
                            DetailList(items = state.generatedTexts)
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun DetailList(
    items: List<Info>?
) {
    LazyColumn(
        modifier = Modifier.sizeIn(maxHeight = 50000.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
        items(
            items = items!!
        ) { item ->
            PageInfo(item)
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun PrevisaoErro(
    errorMessage: String?
) {
    Box(Modifier.fillMaxSize()){
        Text(
            modifier = Modifier.align(Alignment.Center),
            text= errorMessage ?: GENERIC_ERROR,
        )
    }
}

@Composable
fun PrevisaoLoading() {
    Box(Modifier.fillMaxSize()){
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = LocalTheme.current.value.pallete.secondary
        )
    }
}