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
import br.com.plantecerto.ui.theme.ThemeViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopInfoLayout(
    vm: ThemeViewModel = viewModel()
) {
    val pageData = vm.theme.value.pageData

    AnimatedContent(targetState = vm.theme) {
        Box(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = pageData.image),
                contentDescription = null
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
                text = pageData.title,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h1
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = pageData.subtitle,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.h2
            )
        }
    }
}


@Preview
@Composable
fun TopInfoLayoutPrev() {
    TopInfoLayout()
}