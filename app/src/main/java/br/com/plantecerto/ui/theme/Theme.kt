package br.com.plantecerto.ui.theme

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.domain.data.Themes
import br.com.plantecerto.ui.utils.LogCompositions
import br.com.plantecerto.ui.utils.NoRippleTheme

const val ThemeLabelTransition = "ThemeLabelTransition"

val LocalTheme = compositionLocalOf { mutableStateOf(Themes.CORN) }

@Composable
fun PlanteCertoTheme(
    content: @Composable () -> Unit
) {
    val theme = remember { mutableStateOf(Themes.CORN) }

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


