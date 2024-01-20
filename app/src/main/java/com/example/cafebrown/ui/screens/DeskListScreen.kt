package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.viewmodels.DeskListViewModel
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextLabelSmallOnPrimary
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleMediumError
import com.example.cafebrown.ui.components.TextTitleMediumPrimary
import com.example.cafebrown.ui.theme.AppTheme

@Composable
fun DeskListScreen(
    deckViewModel: DeskListViewModel = viewModel(),
    onNavUp: () -> Unit,
    onNavigateToReserve : () -> Unit
) {
    val deskState = deckViewModel.deskState.value
    Scaffold(
        topBar = {
            AppTopAppBar(title = stringResource(id = R.string.desk_reserve), true, onNavUp)
        },
        content = { paddingValues ->
            MainColumn(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.Top,
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    content = {
                        items(deskState.deskList.size) { index ->
                            DeskGridItem(
                                index % 2 != 0,
                                capacity = deskState.deskList[index].capacity,
                                deckNum = deskState.deskList[index].deckNum,
                                isFull = deskState.deskList[index].isFull,
                                onItemClick = onNavigateToReserve
                            )
                        }
                    })
            }
        }
    )
}

@Composable
fun DeskGridItem(
    isOdd: Boolean,
    capacity: String,
    isFull: Boolean = false,
    deckNum: String,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier.height(IntrinsicSize.Max)
            .clickable { onItemClick() },

    ) {

        if (isOdd) {
            Divider(
                modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .padding(vertical = 16.dp)
            )
        }
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp),
                    painter = painterResource(id = R.mipmap.ic_desk), contentDescription = ""
                )
                TextLabelSmallOnPrimary(
                    modifier = Modifier.align(Alignment.Center),
                    text = deckNum
                )
            }
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                TextTitleMedium(
                    text = "${stringResource(id = R.string.capacity_of_desk_is)} ${capacity} ${
                        stringResource(
                            id = R.string.person
                        )
                    }"
                )
                Row {
                    TextTitleMedium(
                        modifier = Modifier.padding(end = 4.dp),
                        text = stringResource(id = R.string.status_at_the_moment_is)
                    )
                    if (isFull) {
                        TextTitleMediumError(text = stringResource(id = R.string.full))
                    } else {
                        TextTitleMediumPrimary(text = stringResource(id = R.string.empty))
                    }

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}

data class DeckItemData(
    val id: Int,
    val capacity: String,
    val deckNum: String,
    val isFull: Boolean
)
//@Preview(showBackground = false, showSystemUi = true)
//@Composable
//fun SimpleView() {
//    AppTheme {
//        Surface {
//            DeskListScreen({})
//        }
//    }
//}