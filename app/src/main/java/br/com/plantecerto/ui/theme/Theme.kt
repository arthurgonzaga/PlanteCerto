package br.com.plantecerto.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.livedata.observeAsState
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
    MaterialTheme(
        colors = currentTheme.value.pallete,
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


