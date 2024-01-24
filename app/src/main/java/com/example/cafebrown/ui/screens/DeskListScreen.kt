package com.example.cafebrown.ui.screens

import android.util.Log
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.DeskListEvent
import com.example.cafebrown.presentation.events.HomeEvent
import com.example.cafebrown.presentation.viewmodels.DeskListViewModel
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.ProgressBarDialog
import com.example.cafebrown.ui.components.TextLabelSmallOnPrimary
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleMediumError
import com.example.cafebrown.ui.components.TextTitleMediumPrimary
import com.example.cafebrown.ui.theme.AppTheme
import com.example.cafebrown.utils.UIText

@Composable
fun DeskListScreen(
    deckViewModel: DeskListViewModel = hiltViewModel(),
    onNavUp: () -> Unit,
    onNavigateToReserve: (deskId: Int, capacity: Int, tableNumber: Int, status: Boolean) -> Unit
) {
    val context = LocalContext.current
    val deskState = deckViewModel.deskState.value
    val errorSnackBarHostState = remember { SnackbarHostState() }


    LaunchedEffect(key1 = true) {
        deckViewModel.uiEventFlow.collect() { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    val result = errorSnackBarHostState
                        .showSnackbar(
                            message = event.message.asString(context),
                            actionLabel = UIText.StringResource(R.string.try_again)
                                .asString(context),
                            duration = SnackbarDuration.Indefinite
                        )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            deckViewModel.onEvent(DeskListEvent.GetDeskList)
                        }

                        else -> {}
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        Log.i("meyti", "LaunchedEffect Call")
        deckViewModel.onEvent(DeskListEvent.GetDeskList)
    }

    if (deskState.isLoading) {
        ProgressBarDialog {}
    }

    Scaffold(
        topBar = {
            AppTopAppBar(title = stringResource(id = R.string.desk_reserve), true, onNavUp)
        },
        snackbarHost = {
            SnackbarHost(errorSnackBarHostState) {
                AppSnackBar(it)
            }
        },
        content = { paddingValues ->
            MainColumn(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.Top,
            ) {
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    content = {
                        items(count = deskState.deskList.size) { index ->
                            DeskGridItem(
                                index % 2 != 0,
                                capacity = deskState.deskList[index].capacity,
                                deckNum = deskState.deskList[index].number,
                                isFull = deskState.deskList[index].status,
                                onItemClick = {
                                    onNavigateToReserve(
                                        deskState.deskList[index].id,
                                        deskState.deskList[index].capacity,
                                        deskState.deskList[index].number,
                                        deskState.deskList[index].status
                                    )
                                }
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
    capacity: Int,
    isFull: Boolean = false,
    deckNum: Int,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
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
                    text = deckNum.toString()
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