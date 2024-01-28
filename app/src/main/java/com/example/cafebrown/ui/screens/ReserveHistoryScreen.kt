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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.data.models.reserveHistory.GetUserReserveResponse
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.MenuEvent
import com.example.cafebrown.presentation.events.ProductDetailEvent
import com.example.cafebrown.presentation.events.ReserveHistoryEvent
import com.example.cafebrown.presentation.viewmodels.ReserveHistoryViewModel
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.EmptyView
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.ProgressBarDialog
import com.example.cafebrown.ui.components.TextLabelSmallOnPrimary
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleMediumError
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.delay

@Composable
fun ReserveHistoryScreen(
    reserveHistoryViewModel: ReserveHistoryViewModel = hiltViewModel(),
    onNavigateToReserve: () -> Unit,
    onNavUp: () -> Unit,
    onExpiredToken: () -> Unit
) {
    val context = LocalContext.current
    val reserveHistoryState = reserveHistoryViewModel.reserveHistoryState.value
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        reserveHistoryViewModel.uiEventFlow.collect { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    snackBarHostState.showSnackbar(message = event.message.asString(context))
                }

                is AppUIEvent.ExpiredToken -> {
                    snackBarHostState.showSnackbar(
                        message = UIText.StringResource(R.string.expired_token)
                            .asString(context)
                    )
                    delay(500)  // the delay of 0.5 seconds
                    onExpiredToken()
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        reserveHistoryViewModel.onEvent(ReserveHistoryEvent.GetListFromServer)
    }

    if (reserveHistoryState.isLoading) {
        ProgressBarDialog()
    }

    Scaffold(
        topBar = {
            AppTopAppBar(title = stringResource(id = R.string.reserve_history), true, onNavUp)
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState) {
                AppSnackBar(it)
            }
        },
        content = {
            MainBox(
                modifier = Modifier.padding(it),
            ) {
                if (reserveHistoryState.reserveList.isNotEmpty()) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxSize(),
                        columns = GridCells.Fixed(2),
                        content = {
                            items(reserveHistoryState.reserveList.size) { index ->
                                ReserveGridItem(
                                    index % 2 != 0,
                                    item = reserveHistoryState.reserveList[index],
                                    onItemClick = onNavigateToReserve
                                )
                            }
                        }
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxHeight().align(Alignment.Center),
                        verticalArrangement = Arrangement.Center
                    ) {
                        EmptyView(text = stringResource(id = R.string.empty_reserve_history_view))
                    }
                }

            }
        }
    )
}

@Composable
fun ReserveGridItem(
    isOdd: Boolean,
    item: GetUserReserveResponse,
//    status: Boolean,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .clickable { onItemClick() }
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
                        .padding(vertical = 16.dp)
                        .size(64.dp),
                    painter = painterResource(id = R.mipmap.ic_desk), contentDescription = ""
                )
                TextLabelSmallOnPrimary(
                    modifier = Modifier.align(Alignment.Center),
                    text = item.table
                )
            }
//                if (status){
            TextTitleMedium(text = item.dateTime)
//                }else{
//                    TextTitleMediumError(text = item.dateTime)
//                }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}