package br.com.plantecerto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.ui.components.*
import br.com.plantecerto.ui.screen.DetailScreen
import br.com.plantecerto.ui.theme.PlanteCertoTheme
import br.com.plantecerto.ui.theme.ThemeViewModel
import br.com.plantecerto.ui.utils.LogCompositions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val vm: ThemeViewModel = viewModel()
            val pagerState = rememberPagerState()
            PlanteCertoTheme() {
                LogCompositions("MainActivity","")
                ListBottomSheet {
                    DetailScreen(pagerState = pagerState)
                }
            }
        }
    }
}