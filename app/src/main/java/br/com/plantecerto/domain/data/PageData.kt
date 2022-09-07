package br.com.plantecerto.domain.data

import androidx.annotation.DrawableRes

/**
 * Created by Arthur Gonzaga on 9/7/2022
 */
data class PageData(
    val title: String,
    val subtitle: String,
    @DrawableRes
    val image: Int,
    val info : List<Info>,
    val plagues : List<Info>,
    val previsions : List<Info>,
)

interface Info {
    val title: String?
    val text: String?
}

data class TextInfo(
    override val title: String?,
    override val text: String?
): Info

data class ImageInfo(
    override val title: String?,
    override val text: String?,
    @DrawableRes
    val drawableId: Int
) : Info