package com.example.cafebrown.ui.screens

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cafebrown.R
import com.example.cafebrown.data.models.menu.GetMenuResponse
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.MenuEvent
import com.example.cafebrown.presentation.viewmodels.MenuListViewModel
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.EmptyView
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.ProgressBarDialog
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.utils.ClickHelper
import com.example.cafebrown.utils.Resource
import com.example.cafebrown.utils.ServerConstants.IMAGE_URL
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuListScreen(
    menuListViewModel: MenuListViewModel = hiltViewModel(),
    onNavigateToProductList: (Int, String) -> Unit,
    onNavUp: () -> Unit,
    onExpiredToken: () -> Unit
) {
    val context = LocalContext.current
    val menuState = menuListViewModel.menuState.value
    val errorSnackBarHostState = remember { SnackbarHostState() }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = menuState.isLoading,
        onRefresh = { menuListViewModel.onEvent(MenuEvent.GetMenuList) }
    )

    LaunchedEffect(key1 = true) {
        menuListViewModel.uiEventFlow.collect { event ->
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
                            menuListViewModel.onEvent(MenuEvent.GetMenuList)
                        }
                        else -> {}
                    }
                }

                is AppUIEvent.ExpiredToken -> {
                    errorSnackBarHostState.showSnackbar(
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
        menuListViewModel.onEvent(MenuEvent.GetMenuList)
    }

    Scaffold(
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.menu),
                isBackVisible = true,
                onBack = onNavUp
            )
        },
        snackbarHost = {
            SnackbarHost(errorSnackBarHostState) {
                AppSnackBar(it)
            }
        },
        content = { paddingValues ->
            MainBox(
                modifier = Modifier
                    .padding(paddingValues)
                    .pullRefresh(pullRefreshState)
            ) {
                if (menuState.menuListState.isNotEmpty()) {
                    MenuGrid(
                        items = menuState.menuListState,
                        onItemClick = {
                            onNavigateToProductList(it, menuState.from)
                        }
                    )
                } else {
                    Column(
                        modifier = Modifier.fillMaxHeight().align(Alignment.Center),
                        verticalArrangement = Arrangement.Center
                    ) {
                        EmptyView(text = stringResource(id = R.string.empty_menu_view))
                    }
                }
                PullRefreshIndicator(
                    refreshing = menuState.isLoading,
                    state = pullRefreshState,
                    modifier = Modifier.align(Alignment.TopCenter),
                )
            }
        }
    )

    if (menuState.isLoading) {
        ProgressBarDialog()
    }
}

@Composable
fun MenuGrid(
    items: List<GetMenuResponse>,
    onItemClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(count = 2),
    ) {
        items(count = items.size) { index ->
            MenuGridItem(
                isOdd = index % 2 != 0,
                item = items[index],
                onItemClick = onItemClick
            )
        }
    }
}

@Composable
fun MenuGridItem(
    isOdd: Boolean,
    item: GetMenuResponse,
    onItemClick: (Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val itemSize = 0.23 * configuration.screenWidthDp.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                ClickHelper
                    .getInstance()
                    .clickOnce { onItemClick(item.id) }
            }
            .height(IntrinsicSize.Max)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = IMAGE_URL + item.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(itemSize),
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextTitleMedium(text = item.title)
            Divider(
                Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            )
        }
        if (!isOdd) {
            Divider(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxHeight()
                    .width(1.dp)
                    .align(Alignment.TopEnd)
            )
        }

    }

}


