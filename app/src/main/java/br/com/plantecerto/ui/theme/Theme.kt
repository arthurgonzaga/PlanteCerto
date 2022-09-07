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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

enum class Themes(val pallete: Colors) {
    CORN(pallete = CornPallete),
    COFFEE(pallete = CoffeePallete),
    BEANS(pallete = BeansPallete),
    SOY(pallete = SoyPallete),
    BANANA(pallete = BananaPallete),
    GUAVA(pallete = GuavaPallete)
}

@Composable
fun PlanteCertoTheme(
    viewModel: ThemeViewModel = viewModel(),
    content: @Composable () -> Unit
) {
    val currentTheme = viewModel.theme.observeAsState(Themes.CORN)
    val transition = updateTransition(targetState = currentTheme, label = "ThemeTransition")

    val primary by transition.animateColor(label = "primary") { it.value.pallete.primary }
    val primaryVariant by transition.animateColor(label = "primaryVariant") { it.value.pallete.primaryVariant }
    val secondary by transition.animateColor(label = "secondary") { it.value.pallete.secondary }
    val background by transition.animateColor(label = "background") { it.value.pallete.background }
    val onBackground by transition.animateColor(label = "onBackground") { it.value.pallete.onBackground }

    
    MaterialTheme(
        colors = currentTheme.value.pallete.copy(
            primary = primary,
            primaryVariant = primaryVariant,
            secondary = secondary,
            background = background,
            onBackground = onBackground,
        ),
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

class ThemeViewModel: ViewModel() {
    private val _theme = MutableLiveData(Themes.CORN)
    val theme: LiveData<Themes> = _theme

    fun onThemeChange(theme: Themes) {
        _theme.value = theme
    }
}


