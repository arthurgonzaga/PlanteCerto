package br.com.plantecerto.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.plantecerto.domain.data.entity.model.ImageInfo
import br.com.plantecerto.domain.data.entity.model.Info
import br.com.plantecerto.domain.data.entity.model.TextInfo
import br.com.plantecerto.ui.theme.CornPallete
import br.com.plantecerto.ui.theme.Pallete
import br.com.plantecerto.ui.theme.PlanteCertoTheme
import br.com.plantecerto.ui.theme.getPallete

/**
 * Created by Arthur Gonzaga on 9/11/2022
 */
@Composable
fun PageInfo(
    info: Info,
    pallete: Pallete = getPallete()
) {

    val localDensity = LocalDensity.current
    val height = remember { mutableStateOf(0.dp) }
    Row(
        modifier = Modifier
            .padding(start = 24.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = pallete.primaryVariant,
                    shape = MaterialTheme.shapes.medium
                )
                .width(3.dp)
                .height(height = height.value)

        )
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    height.value = with(localDensity) { coordinates.size.height.toDp() }
                }
        ){
            AnimatedVisibility(visible = info.title != null) {
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = info.title!!,
                    style = MaterialTheme.typography.subtitle1,
                    color = pallete.onBackground
                )
            }
            AnimatedVisibility(visible = info.text != null) {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = info.text!!,
                    style = MaterialTheme.typography.body1,
                    color = pallete.onBackground
                )
            }
            AnimatedVisibility(visible = info is ImageInfo) {
                Image(
                    painter = painterResource(id = (info as ImageInfo).drawableId),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PageInfoPrev() {
    PlanteCertoTheme {
        PageInfo(info = TextInfo(title = "title", "text"), CornPallete)
    }
}