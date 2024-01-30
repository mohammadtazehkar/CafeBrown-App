package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult.*
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.data.models.transaction.UserTransactionsData
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.TransactionEvent
import com.example.cafebrown.presentation.viewmodels.TransactionViewModel
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.EmptyView
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.ProgressBarDialog
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.ui.theme.AppTheme
import com.example.cafebrown.utils.ClickHelper
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.delay
import java.text.NumberFormat
import java.util.Locale


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TransactionScreen(
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    onNavUp: () -> Unit,
    onExpiredToken: () -> Unit
) {
    val context = LocalContext.current
    var transactionState = transactionViewModel.transactionState.value

    val pullRefreshState = rememberPullRefreshState(
        refreshing = transactionState.isLoading,
        onRefresh = { transactionViewModel.onEvent(TransactionEvent.GetTransactionListFromServer) }
    )
    val snackbarHostState = remember { SnackbarHostState() }
    val successSnackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        transactionViewModel.screenSharedFlow.collect { uiEvent ->
            when (uiEvent) {
                is AppUIEvent.ShowMessage -> {
                    if (!uiEvent.isError) {
                        successSnackbarHostState.showSnackbar(
                            message = uiEvent.message.asString(context),
                            duration = SnackbarDuration.Short
                        )
                        //return@collect
                    } else {
                        val result = snackbarHostState.showSnackbar(
                            message = uiEvent.message.asString(context),
                            actionLabel = UIText.StringResource(R.string.try_again)
                                .asString(context),
                            duration = SnackbarDuration.Indefinite
                        )
                        when (result) {
                            ActionPerformed -> {
                                transactionViewModel.onEvent(TransactionEvent.GetTransactionListFromServer)
                            }

                            Dismissed -> {}
                        }
                    }
                }

                is AppUIEvent.ExpiredToken -> {
                    snackbarHostState.showSnackbar(
                        message = UIText.StringResource(resId = R.string.expired_token)
                            .asString(context)
                    )
                    delay(500)
                    onExpiredToken()
                }
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        transactionViewModel.onEvent(TransactionEvent.GetTransactionListFromServer)
    }

    if (transactionState.isLoading) {
        ProgressBarDialog()
    }

    if (transactionState.isDialogVisible) {
        IncreaseBalanceDialog(balance = transactionState.balance.toString(), onDismissRequest = {
            transactionViewModel.onEvent(TransactionEvent.ChangeDialogVisibility(false))
        }, onConfirmation = {
            transactionViewModel.onEvent(
                TransactionEvent.ChangeDialogVisibility(false)
            )
            transactionViewModel.onEvent(TransactionEvent.PostIncreaseBalanceToServer)
        }, onChangeBalance = { newVal ->
            transactionViewModel.onEvent(
                TransactionEvent.ChangeIncreaseBalanceTextField(
                    newVal
                )
            )
        }, increaseBalance = transactionState.increaseBalance
        )
    }

    Scaffold(topBar = {
        AppTopAppBar(
            title = stringResource(id = R.string.transaction),
            isBackVisible = true,
            onBack = onNavUp
        )
    }, snackbarHost = {
        SnackbarHost(snackbarHostState) {
            AppSnackBar(it)
        }
        SnackbarHost(successSnackbarHostState) {
            AppSnackBar(it,false)
        }
    }) {
        MainBox(modifier = Modifier
            .padding(it)
            .pullRefresh(pullRefreshState)) {
            ConstraintLayout(
                modifier = Modifier.fillMaxSize()
            ) {
                val (emptyColumn, lazyGrid, bottomBar) = createRefs()
                if (transactionState.transactionList.size == 0) {
                    Column(modifier = Modifier.constrainAs(emptyColumn) {
                        centerVerticallyTo(parent)
                        centerHorizontallyTo(parent)
                    }) {
                        EmptyView(text = stringResource(id = R.string.empty_view))
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.constrainAs(lazyGrid) {
                            top.linkTo(parent.top)
                            bottom.linkTo(bottomBar.top)
                            height = Dimension.fillToConstraints
                        }
                    ) {
                        items(count = transactionState.transactionList.size) { index ->
                            TransactionGridItem(
                                transactionData = transactionState.transactionList[index],
                                shouldShowDivider = index % 2 == 0
                            )
                        }
                    }
                }
                TransactionBottomBar(
//                    Modifier.align(Alignment.BottomCenter),
                    Modifier.constrainAs(bottomBar) {
                        bottom.linkTo(parent.bottom)
                    },
                    transactionState.balance.toString(),
                    {
                        transactionViewModel.onEvent(
                            TransactionEvent.ChangeDialogVisibility(true)
                        )
                    })
            }
            PullRefreshIndicator(
                refreshing = transactionState.isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
            )
        }

    }

}

@Composable
fun TransactionGridItem(
    modifier: Modifier = Modifier, transactionData: UserTransactionsData, shouldShowDivider: Boolean
) {
    Box(modifier = modifier.height(IntrinsicSize.Max)) {
        if (shouldShowDivider) Divider(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxHeight()
                .width(1.dp)
                .align(Alignment.TopEnd)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(64.dp), painter = painterResource(
                    id = when (transactionData.transactionTypeId) {
                        0 -> R.mipmap.ic_wallet
                        1 -> R.mipmap.ic_transaction
                        else -> R.mipmap.ic_wallet
                    }
                ), contentDescription = transactionData.transactionType
            )
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                TextTitleMedium(
                    text = "${
                        NumberFormat.getNumberInstance(Locale.US).format(transactionData.amount)
                    } ${stringResource(id = R.string.toman)}",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            TextTitleSmall(text = transactionData.transactionType!!)
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                TextTitleSmall(
                    text = transactionData.dateTime!!,
                    modifier = Modifier
                        .weight(2.0F)
                        .padding(start = 12.dp),
                    textAlign = TextAlign.Left
                )
                TextTitleSmall(
                    text = when (transactionData.status) {
                        true -> stringResource(id = R.string.successfully)
                        false -> stringResource(id = R.string.failed)
                    }, modifier = Modifier.padding(end = 10.dp)
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 8.dp))
        }

    }
}

@Composable
fun TransactionBottomBar(modifier: Modifier, price: String, onClick: () -> Unit) {
//    val context = LocalContext.current
    Row(
        modifier = modifier.background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            TextTitleMedium(
                text = "${NumberFormat.getNumberInstance(Locale.US).format(price.toLong())} ${
                    stringResource(
                        id = R.string.toman
                    )
                }",
                modifier = Modifier
                    .weight(2.0F)
                    .padding(8.dp),
                textAlign = TextAlign.End
            )
            TextTitleSmall(
                text = stringResource(id = R.string.your_balance_is),
                modifier = Modifier.padding(start = 4.dp)
            )
            IconButton(modifier = Modifier.padding(start = 4.dp), onClick = {
                ClickHelper.getInstance().clickOnce {
                    onClick()
//                    Toast.makeText(context, "test open dialog", Toast.LENGTH_SHORT).show()
                }
            }) {

                Image(
                    painter = painterResource(id = R.mipmap.ic_add_brown),
                    contentDescription = "Add",
                    modifier = Modifier.size(32.dp)
//                        .clickable {
//                            Toast
//                                .makeText(context, "test open dialog", Toast.LENGTH_SHORT)
//                                .show()
//                        }
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun preview() {
    AppTheme {
        TransactionScreen(viewModel(), { }, {})
//        var trData = mutableListOf(
//            TransactionItemData(
//                R.drawable.logo,
//                "mamali",
//                "2000",
//                "خرید سفارش",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            )
//        )
//        trData.forEachIndexed { index, item ->
//            TransactionGridItem(
//                transactionData = item,
//                shouldShowDivider = index % 2 == 0
//            )
//        }
    }
}