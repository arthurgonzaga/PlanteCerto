@file:SuppressLint("ConflictingOnColor")

package br.com.plantecerto.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material.lightColors


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
