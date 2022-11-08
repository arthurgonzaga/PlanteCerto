package br.com.plantecerto.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import br.com.plantecerto.domain.data.entity.model.Themes
import br.com.plantecerto.ui.utils.NoRippleTheme

const val ThemeLabelTransition = "ThemeLabelTransition"

val LocalTheme = compositionLocalOf { mutableStateOf(Themes.HOME) }

@Composable
fun PlanteCertoTheme(
    content: @Composable () -> Unit
) {
    val theme = rememberSaveable { mutableStateOf(Themes.HOME) }

    CompositionLocalProvider(
        LocalRippleTheme provides NoRippleTheme,
        LocalTheme provides theme
    ) {
        MaterialTheme(
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

@Composable
fun getPallete(): Pallete {
    return updateTransitionTheme(LocalTheme.current.value)
}


