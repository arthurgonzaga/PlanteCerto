package br.com.plantecerto.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import br.com.plantecerto.R
import br.com.plantecerto.domain.data.entity.model.Themes
import br.com.plantecerto.ui.theme.LocalTheme
import br.com.plantecerto.ui.theme.getPallete
import br.com.plantecerto.ui.utils.LogCompositions


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopInfoLayout() {

    LogCompositions("TopInfoLayout","")
    val theme = LocalTheme.current.value
    val pageData = theme.pageData

    Box {
        Box(Modifier.fillMaxWidth()) {
            pageData.image?.let { imageId ->
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    alignment = Alignment.TopEnd
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 52.dp,
                    start = 36.dp
                )
        ) {
            val logoImage = ImageBitmap.imageResource(id = R.drawable.logo)
            Canvas(
                modifier = Modifier.size(
                    height = 45.dp,
                    width = 67.dp,
                ),
                onDraw = {
                    drawImage(
                        image = logoImage,
                        blendMode = if(theme != Themes.HOME) BlendMode.Luminosity else BlendMode.Overlay
                    )
                }
            )
            Spacer(Modifier.height(44.dp))
            Text(
                modifier = Modifier.fillMaxWidth(if(theme != Themes.HOME) 0.5f else 0.8f),
                text = pageData.title,
                color = getPallete().onBackground,
                style = MaterialTheme.typography.h1
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = pageData.subtitle,
                color = getPallete().onBackground,
                style = MaterialTheme.typography.h2
            )
        }
    }
}
