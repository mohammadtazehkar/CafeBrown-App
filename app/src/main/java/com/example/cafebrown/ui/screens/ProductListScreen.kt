package com.example.cafebrown.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.cafebrown.R
import com.example.cafebrown.data.models.product.ProductResponse
import com.example.cafebrown.data.models.product.SubMenuResponse
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.MenuEvent
import com.example.cafebrown.presentation.events.ProductListEvent
import com.example.cafebrown.presentation.viewmodels.ProductListViewModel
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.EmptyView
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleMediumPrimary
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.ui.components.TextTitleSmallPrimary
import com.example.cafebrown.utils.ClickHelper
import com.example.cafebrown.utils.Destinations.RESERVE_SCREEN
import com.example.cafebrown.utils.ServerConstants.IMAGE_URL
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import java.text.NumberFormat
import java.util.Locale

@Composable
fun ProductListScreen(
    productListViewModel: ProductListViewModel = hiltViewModel(),
    onNavigateToDetail: (Int, String) -> Unit,
    onNavUp: () -> Unit,
    onExpiredToken: () -> Unit
) {
    val productState = productListViewModel.productListState.value
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        productListViewModel.uiEventFlow.collect { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    val result = snackBarHostState
                        .showSnackbar(
                            message = event.message.asString(context),
                            actionLabel = UIText.StringResource(R.string.try_again)
                                .asString(context),
                            duration = SnackbarDuration.Indefinite
                        )
                    when (result) {
                        SnackbarResult.ActionPerformed -> {
                            productListViewModel.onEvent(ProductListEvent.GetListFromServer)
                        }

                        else -> {}
                    }
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
    LaunchedEffect(Unit){
        if (!productState.hasRunEffect) {
            // Run your code here that you want to execute only once
            productListViewModel.onEvent(ProductListEvent.UpdateHasRunEffect(true))
            productListViewModel.onEvent(ProductListEvent.GetListFromServer)
        }
    }

    Scaffold(
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.products),
                isBackVisible = true,
                onBack = onNavUp
            )
        },
        snackbarHost = {
            SnackbarHost(snackBarHostState) {
                AppSnackBar(it)
            }
        },
        content = { paddingValues ->
            MainColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                SubCategoryList(
                    selectedCategoryId = productState.selectedCategoryId,
                    items = productState.subCategoryListState,
                    onItemClick = { subCategoryId ->
                        productListViewModel.onEvent(
                            ProductListEvent.SelectSubCategory(
                                subCategoryId
                            )
                        )
                    }
                )
                if (productState.productListState.isNotEmpty()) {
                    ProductGrid(
                        items = productState.productListState,
                        onItemClick = onNavigateToDetail,
                        onDecrease = { productListViewModel.onEvent(ProductListEvent.DeCreaseCount) },
                        onIncrease = { productListViewModel.onEvent(ProductListEvent.InCreaseCount) },
                        selectVisibility = productState.from == RESERVE_SCREEN,
                        selectedCount = productState.selectedCount,
                        total = productState.total
                    )
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        EmptyView(
                            text = stringResource(
                                id =
                                if (productState.subCategoryListState.isEmpty()) {
                                    R.string.empty_product_list_view

                                } else {
                                    R.string.empty_product_list_view_for_sub_category
                                }
                            )
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun SubCategoryList(
    selectedCategoryId: Int,
    items: List<SubMenuResponse>,
    onItemClick: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            item {
                Spacer(modifier = Modifier.width(1.dp))
            }
            items(items.size) { index ->
                SubCategoryListItem(
                    selectedCategoryId = selectedCategoryId,
                    item = items[index],
                    onItemClick = onItemClick
                )
            }
            item {
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
    )
}

@Composable
fun SubCategoryListItem(
    selectedCategoryId: Int,
    item: SubMenuResponse,
    onItemClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)// clip to the circle shape
            .border(
                1.dp,
                if (item.id == selectedCategoryId) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onPrimaryContainer
                },
                CircleShape
            )
            .clickable {
                ClickHelper
                    .getInstance()
                    .clickOnce { onItemClick(item.id) }
            }
    ) {
        if (item.id == selectedCategoryId) {
            TextTitleSmallPrimary(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = item.title
            )
        } else {
            TextTitleSmall(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                text = item.title
            )
        }
    }
}

@Composable
fun ProductGrid(
    items: List<ProductResponse>,
    onItemClick: (Int, String) -> Unit,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    selectVisibility: Boolean,
    selectedCount: Int,
    total: Int
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(count = 2),
    ) {
        items(count = items.size) { index ->
            ProductGridItem(
                isOdd = index % 2 != 0,
                item = items[index],
                onItemClick = onItemClick,
                onDecrease,
                onIncrease,
                selectVisibility,
                selectedCount,
                total
            )
        }
    }
}

@Composable
fun ProductGridItem(
    isOdd: Boolean,
    item: ProductResponse,
    onItemClick: (Int, String) -> Unit,
    onDecrease: () -> Unit,
    onIncrease: () -> Unit,
    selectVisibility: Boolean,
    selectedCount: Int,
    total: Int
) {
    val configuration = LocalConfiguration.current
    val itemSize = 0.23 * configuration.screenWidthDp.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                ClickHelper
                    .getInstance()
                    .clickOnce { onItemClick(item.id, item.title) }
            }
            .height(IntrinsicSize.Max)
    ) {

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = IMAGE_URL + item.url,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(itemSize)
                    .clip(CircleShape)// clip to the circle shape
                    .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextTitleMedium(text = item.title)
                Spacer(modifier = Modifier.height(8.dp))
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Row(modifier = Modifier.fillMaxWidth()){
                        TextTitleMediumPrimary(text = stringResource(id = R.string.price_is))
                        Spacer(modifier = Modifier.weight(1.0f))
                        TextTitleMedium(text = NumberFormat.getNumberInstance(Locale.US).format(item.price), modifier = Modifier.padding(end = 4.dp))
                        TextTitleSmall(text = stringResource(id = R.string.toman))
                    }
                }
            }
            if (selectVisibility) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(onClick = onDecrease) {
                        Image(
                            painter = painterResource(id = R.mipmap.ic_minus_brown),
                            contentDescription = "minus",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    TextTitleSmall(
                        modifier = Modifier.weight(1f),
                        text = "$selectedCount/$total"
                    )

                    IconButton(onClick = onIncrease) {
                        Image(
                            painter = painterResource(id = R.mipmap.ic_add_brown),
                            contentDescription = "add",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
            Divider(
                Modifier
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
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