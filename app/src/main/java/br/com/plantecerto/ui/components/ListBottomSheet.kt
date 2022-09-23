package br.com.plantecerto.ui.components

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.R
import br.com.plantecerto.domain.data.Themes
import br.com.plantecerto.ui.theme.*
import br.com.plantecerto.ui.utils.LogCompositions
import br.com.plantecerto.ui.utils.noRippleClickable
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class ListData(
    @DrawableRes
    val imageId: Int,
    val title: String,
    val theme: Themes,
)

private val DefaultList = listOf(
    ListData(R.drawable.ic_milho, "Milho", Themes.CORN),
    ListData(R.drawable.ic_cafe, "Café", Themes.COFFEE),
    ListData(R.drawable.ic_feijao, "Feijão", Themes.BEANS),
    ListData(R.drawable.ic_soja, "Soja", Themes.SOY),
    ListData(R.drawable.ic_banana, "Banana", Themes.BANANA),
    ListData(R.drawable.ic_goiaba, "Goiaba", Themes.GUAVA),
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListBottomSheet(
    content: @Composable (PaddingValues) -> Unit
) {
    LogCompositions("ListBottomSheet","")
    val coroutineScope = rememberCoroutineScope()

    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val theme = LocalTheme.current
    val window = (LocalContext.current as Activity).window

    BottomSheetScaffold(
        sheetPeekHeight = 44.dp,
        sheetBackgroundColor = White,
        sheetShape = ListBottomSheetShape,
        scaffoldState = state,
        sheetContent = {
            Column(
                modifier = Modifier.noRippleClickable {
                    coroutineScope.launch {
                        state.bottomSheetState.expand()
                    }
                }
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .background(color = Gray, MaterialTheme.shapes.medium)
                        .size(width = 36.dp, height = 4.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn {
                    items(DefaultList)  { item ->
                        ListItem(
                            data = item,
                            onClick = {
                                theme.value = it.theme
                                window.decorView.setBackgroundColor( it.theme.pallete.background.toArgb())
                                coroutineScope.launch {
                                    state.bottomSheetState.collapse()
                                }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        },
        content = content
    )
}

@Composable
fun ListItem(
    data: ListData,
    onClick: (data: ListData) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(62.dp)
            .noRippleClickable { onClick.invoke(data) }
            .padding(horizontal = 39.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)
                .border(
                    width = 2.dp,
                    color = getPallete().secondary,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            Image(
                modifier = Modifier.padding(2.dp),
                painter = painterResource(id = data.imageId),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = data.title,
            color = getPallete().secondary
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_arrow),
            colorFilter = ColorFilter.tint(
                color = getPallete().secondary
            ),
            contentDescription = null,
        )
    }
}
