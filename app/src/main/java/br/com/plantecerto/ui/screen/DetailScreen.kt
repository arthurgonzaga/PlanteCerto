package br.com.plantecerto.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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


@Composable
fun DetailScreen() {
    val vm: ThemeViewModel = viewModel()

    val tabTitles = listOf("Informações", "Pragas", "Previsões")
    val tabIndexSelected = remember { mutableStateOf(0) }

    PlanteCertoTheme(viewModel = vm) {
        ListBottomSheet {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopInfoLayout()
                TabSelector(
                    modifier = Modifier.offset(y = (-60).dp),
                    titles = tabTitles,
                    selectedIndex = tabIndexSelected
                )
            }
        }
    }
}