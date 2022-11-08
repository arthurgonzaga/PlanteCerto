package br.com.plantecerto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import br.com.plantecerto.domain.data.entity.model.Themes
import br.com.plantecerto.ui.components.*
import br.com.plantecerto.ui.screen.detail.DetailScreen
import br.com.plantecerto.ui.screen.HomeScreen
import br.com.plantecerto.ui.theme.LocalTheme
import br.com.plantecerto.ui.theme.PlanteCertoTheme
import br.com.plantecerto.ui.utils.LogCompositions
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val pagerState = rememberPagerState()
            WindowInsets {
                PlanteCertoTheme() {
                    LogCompositions("MainActivity","")
                    ListBottomSheet {
                        val theme = LocalTheme.current.value
                        when(theme) {
                            Themes.HOME -> {
                                HomeScreen()
                            }
                            Themes.REFERENCES -> {

                            }
                            else -> {
                                DetailScreen(
                                    theme = theme,
                                    pagerState = pagerState
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}