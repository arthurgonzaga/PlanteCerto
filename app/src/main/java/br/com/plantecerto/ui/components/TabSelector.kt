package br.com.plantecerto.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.plantecerto.ui.utils.noRippleClickable

/**
 * Created by Arthur Gonzaga on 9/7/2022
 */
@Composable
fun TabSelector(
    modifier: Modifier = Modifier,
    selectedIndex: MutableState<Int>,
    titles: List<String>
) {
    TabRow(
        modifier = modifier.height(55.dp).padding(horizontal = 28.dp).fillMaxWidth(),
        divider = {},
        backgroundColor = Color.Transparent,
        indicator = { TabIndicator(tabPositions = it, selected = selectedIndex.value) },
        selectedTabIndex = selectedIndex.value
    ) {
        titles.forEachIndexed { index, s ->
            val isSelected = selectedIndex.value == index

            Tab(
                selected = isSelected,
                onClick = {
                    selectedIndex.value = index
                },
            ) {
                Text(
                    text = s,
                    style = TextStyle(
                        letterSpacing = 1.5.sp,
                        fontWeight = if(isSelected) {
                            FontWeight.Bold
                        } else {
                            FontWeight.Normal
                        }
                    ),
                    color = if(isSelected) {
                        MaterialTheme.colors.onBackground
                    } else {
                        MaterialTheme.colors.secondary
                    }
                )
            }
        }

    }
}

@Composable
fun TabIndicator(
    tabPositions: List<TabPosition>,
    selected: Int
) {
    Box(
        Modifier
            .tabIndicatorOffset(tabPositions[selected])
            .height(4.dp)
            .padding(horizontal = 42.dp)
            .offset(y = (-5).dp)
            .clip(shape = MaterialTheme.shapes.medium)
            .background(
                color = MaterialTheme.colors.onBackground,
                shape = MaterialTheme.shapes.medium
            )
    )
}