package br.com.plantecerto.ui.theme

import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.domain.data.Themes

const val ThemeLabelTransition = "ThemeLabelTransition"

@Composable
fun PlanteCertoTheme(
    viewModel: ThemeViewModel = viewModel(),
    content: @Composable () -> Unit
) {
    val transition = updateTransition(targetState = viewModel.theme, label = ThemeLabelTransition)

    MaterialTheme(
        colors = lightColors(
            primary = transition.animateColor(label = ThemeLabelTransition) { it.value.pallete.primary }.value,
            primaryVariant = transition.animateColor(label = ThemeLabelTransition) { it.value.pallete.primaryVariant }.value,
            secondary = transition.animateColor(label = ThemeLabelTransition) { it.value.pallete.secondary }.value,
            background = transition.animateColor(label = ThemeLabelTransition) { it.value.pallete.background }.value,
            onBackground = transition.animateColor(label = ThemeLabelTransition) { it.value.pallete.onBackground }.value,
        ),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
    LaunchedEffect(key1 = true) {
        viewModel.init()
    }
}

class ThemeViewModel: ViewModel() {
    private val _theme = mutableStateOf(Themes.CORN)
    val theme: State<Themes> = _theme

    fun init(){
        Themes.values().forEach { _theme.value = it }
        _theme.value = Themes.CORN
    }

    fun onThemeChange(theme: Themes) {
        _theme.value = theme
    }
}


