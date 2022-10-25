@file:SuppressLint("ConflictingOnColor")

package br.com.plantecerto.ui.theme

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.plantecerto.domain.data.model.Themes

data class Pallete(
    val primary: Color,
    val primaryVariant: Color,
    val secondary: Color,
    val background: Color,
    val onBackground: Color,
)

@Composable
fun updateTransitionTheme(themes: Themes): Pallete {
    val transition = updateTransition(themes, label = ThemeLabelTransition)
    val primary = transition.animateColor(label = ThemeLabelTransition) { it.pallete.primary }.value
    val primaryVariant = transition.animateColor(label = ThemeLabelTransition) { it.pallete.primaryVariant }.value
    val secondary = transition.animateColor(label = ThemeLabelTransition) { it.pallete.secondary }.value
    val background = transition.animateColor(label = ThemeLabelTransition) { it.pallete.background }.value
    val onBackground = transition.animateColor(label = ThemeLabelTransition) { it.pallete.onBackground }.value
    return Pallete(primary, primaryVariant, secondary, background, onBackground)
}

val CornPallete = Pallete(
    primary = GreenPrimary,
    primaryVariant = GreenPrimaryVariant,
    secondary = GreenSecondary,
    background = GreenPrimary,
    onBackground = White
)

val CoffeePallete = Pallete(
    primary = BrownPrimary,
    primaryVariant = BrownPrimaryVariant,
    secondary = BrownSecondary,
    background = BrownPrimary,
    onBackground = White
)

val BeansPallete = CoffeePallete

val SoyPallete = Pallete(
    primary = BrightYellowPrimary,
    primaryVariant = BrightYellowPrimaryVariant,
    secondary = BrightYellowSecondary,
    background = BrightYellowPrimary,
    onBackground = BrightYellowOnBackground
)

val BananaPallete = Pallete(
    primary = YellowPrimary,
    primaryVariant = YellowPrimaryVariant,
    secondary = YellowSecondary,
    background = YellowPrimary,
    onBackground = YellowSecondaryOnBackground
)

val GuavaPallete = Pallete(
    primary = BrightRedPrimary,
    primaryVariant = BrightRedPrimaryVariant,
    secondary = BrightRedSecondary,
    background = BrightRedPrimary,
    onBackground = BrightRedSecondaryOnBackground
)
