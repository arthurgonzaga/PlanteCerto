@file:SuppressLint("ConflictingOnColor")

package br.com.plantecerto.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val CornPallete = lightColors(
    primary = GreenPrimary,
    primaryVariant = GreenPrimaryVariant,
    secondary = GreenSecondary,
    background = GreenPrimary,
    onBackground = White
)

val CoffeePallete = lightColors(
    primary = BrownPrimary,
    primaryVariant = BrownPrimaryVariant,
    secondary = BrownSecondary,
    background = BrownPrimary,
    onBackground = White
)

val BeansPallete = CoffeePallete

val SoyPallete = lightColors(
    primary = BrightYellowPrimary,
    primaryVariant = BrightYellowPrimaryVariant,
    secondary = BrightYellowSecondary,
    background = BrightYellowPrimary,
    onBackground = BrightYellowOnBackground
)

val BananaPallete = lightColors(
    primary = YellowPrimary,
    primaryVariant = YellowPrimaryVariant,
    secondary = YellowSecondary,
    background = YellowPrimary,
    onBackground = YellowSecondaryOnBackground
)

val GuavaPallete = lightColors(
    primary = BrightRedPrimary,
    primaryVariant = BrightRedPrimaryVariant,
    secondary = BrightRedSecondary,
    background = BrightRedPrimary,
    onBackground = BrightRedSecondaryOnBackground
)


@Composable
fun PlanteCertoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}



