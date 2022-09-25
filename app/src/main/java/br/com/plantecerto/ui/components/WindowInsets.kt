package br.com.plantecerto.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import br.com.plantecerto.ui.theme.LocalTheme
import br.com.plantecerto.ui.theme.getPallete
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * Created by Arthur Gonzaga on 9/25/2022
 */
@Composable
fun WindowInsets(
    modifier: Modifier = Modifier,
    content: @Composable ()-> Unit
) {
    // Remember a SystemUiController
    val systemUiController = rememberSystemUiController()
    val darkIcons = LocalTheme.current.value.pallete.background.luminance() > 0.5


    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = false
        )

        systemUiController.setNavigationBarColor(
            color = Color.White,
            darkIcons = true
        )
    }

    Box(modifier.navigationBarsPadding()) {
        content()
    }
}