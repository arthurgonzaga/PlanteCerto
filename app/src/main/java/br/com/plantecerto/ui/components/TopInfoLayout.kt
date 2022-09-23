package br.com.plantecerto.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.R
import br.com.plantecerto.ui.theme.LocalTheme
import br.com.plantecerto.ui.theme.getPallete
import br.com.plantecerto.ui.utils.LogCompositions


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopInfoLayout() {

    LogCompositions("TopInfoLayout","")
    val pageData = LocalTheme.current.value.pageData

    Box {
        Box(Modifier.fillMaxWidth()) {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = pageData.image),
                contentDescription = null,
                alignment = Alignment.TopEnd
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 24.dp,
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
                        blendMode = BlendMode.Luminosity
                    )
                }
            )
            Spacer(Modifier.height(44.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
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
