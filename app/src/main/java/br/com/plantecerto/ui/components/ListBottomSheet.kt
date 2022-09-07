package br.com.plantecerto.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.plantecerto.R
import br.com.plantecerto.ui.theme.*
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
    ListData(R.drawable.ic_launcher_background, "Milho", Themes.CORN),
    ListData(R.drawable.ic_launcher_background, "Café", Themes.COFFEE),
    ListData(R.drawable.ic_launcher_background, "Feijão", Themes.BEANS),
    ListData(R.drawable.ic_launcher_background, "Soja", Themes.SOY),
    ListData(R.drawable.ic_launcher_background, "Banana", Themes.BANANA),
    ListData(R.drawable.ic_launcher_background, "Goiaba", Themes.GUAVA),
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListBottomSheet(
    vm: ThemeViewModel = viewModel(),
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        sheetPeekHeight = 38.dp,
        sheetBackgroundColor = White,
        sheetShape = ListBottomSheetShape,
        scaffoldState = state,
        sheetContent = {
            Column(
                modifier = Modifier
                    .noRippleClickable {
                        coroutineScope.launch {
                            state.bottomSheetState.expand()
                        }
                },
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                LazyColumn {
                    items(DefaultList) { item ->
                        ListItem(
                            data = item,
                            onClick = {
                                coroutineScope.launch { state.bottomSheetState.collapse() }
                                coroutineScope.launch { vm.onThemeChange(it.theme) }
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        },
        content = { content() },
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
                    color = MaterialTheme.colors.secondary,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            Image(
                painter = painterResource(id = data.imageId),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = data.title,
            color = MaterialTheme.colors.secondary
        )
        Spacer(modifier = Modifier.weight(1.0f))
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = data.imageId),
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ListBottomSheetPrev() {
    ListBottomSheet() {}
}

@Preview(showBackground = true)
@Composable
fun ListItemPrev() {
    ListItem(ListData(R.drawable.ic_launcher_background, "Teste titulo", Themes.GUAVA)) {}
}