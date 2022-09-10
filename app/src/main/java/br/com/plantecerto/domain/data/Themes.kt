package br.com.plantecerto.domain.data

import androidx.compose.material.Colors
import br.com.plantecerto.domain.info.*
import br.com.plantecerto.ui.theme.*

/**
 * Created by Arthur Gonzaga on 9/7/2022
 */
enum class Themes(val pallete: Pallete, val pageData: PageData) {
    CORN(pallete = CornPallete, pageData = CornPageData),
    COFFEE(pallete = CoffeePallete, pageData = CoffeePageData),
    BEANS(pallete = BeansPallete, pageData = BeansPageData),
    SOY(pallete = SoyPallete, pageData = SoyPageData),
    BANANA(pallete = BananaPallete, pageData = BananaPageData),
    GUAVA(pallete = GuavaPallete, pageData = GuavaPageData)
}