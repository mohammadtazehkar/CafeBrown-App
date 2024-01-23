package com.example.cafebrown.ui.screens

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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ProductListEvent
import com.example.cafebrown.presentation.viewmodels.ProductListViewModel
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextLabelSmall
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.ui.components.TextTitleSmallPrimary
import com.example.cafebrown.ui.theme.AppTheme
import com.example.cafebrown.utils.ClickHelper
import com.example.cafebrown.utils.Destinations.RESERVE_SCREEN
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductListScreen(
    productListViewModel: ProductListViewModel = viewModel(),
    onNavigateToDetail: (Int, String) -> Unit,
    onNavUp: () -> Unit,
) {
    val productState = productListViewModel.productListState.value
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        productListViewModel.uiEventFlow.collectLatest { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    snackBarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                else -> {}
            }
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
                    items = productState.subCategoryListState,
                    onItemClick = { subCategoryId ->
                        productListViewModel.onEvent(
                            ProductListEvent.SelectSubCategory(
                                subCategoryId
                            )
                        )
                    }
                )
//                if (categoryList.isNotEmpty()) {
                ProductGrid(
                    items = productState.productListState,
                    onItemClick = onNavigateToDetail,
                    onDecrease = { productListViewModel.onEvent(ProductListEvent.DeCreaseCount) },
                    onIncrease = { productListViewModel.onEvent(ProductListEvent.InCreaseCount) },
                    selectVisibility = productState.from == RESERVE_SCREEN,
                    selectedCount = productState.selectedCount,
                    total = productState.total
                )
//                } else {
//                    Column(
//                        modifier = Modifier.fillMaxHeight(),
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        EmptyView(text = stringResource(id = R.string.empty_category_list))
//                    }
//                }
            }
        }
    )
}

@Composable
fun SubCategoryList(
    items: List<SubCategoryItemData>,
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
                SubCategoryListItem(item = items[index], onItemClick = onItemClick)
            }
            item {
                Spacer(modifier = Modifier.width(1.dp))
            }
        }
    )
}

@Composable
fun SubCategoryListItem(
    item: SubCategoryItemData,
    onItemClick: (Int) -> Unit
) {
//    if (item.isSelected){
    Column(
        modifier = Modifier
            .clip(MaterialTheme.shapes.small)// clip to the circle shape
            .border(
                1.dp,
                if (item.isSelected) {
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
        if (item.isSelected) {
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
//    }else{
//        Column (
//            modifier = Modifier
//                .clip(MaterialTheme.shapes.small)// clip to the circle shape
//                .border(1.dp, MaterialTheme.colorScheme.onPrimaryContainer, CircleShape)
//                .clickable {
//                    ClickHelper
//                        .getInstance()
//                        .clickOnce { onItemClick(item.id) }
//                }
//        ){
//            TextTitleSmall(modifier = Modifier.padding(horizontal = 16.dp,vertical = 4.dp),text = item.title)
//        }
//
//    }
}

@Composable
fun ProductGrid(
    items: List<ProductListItemData>,
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
    item: ProductListItemData,
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

//            AsyncImage(
//                model = IMAGE_URL + item.imageUrl,
//                contentDescription = null,
//                modifier = Modifier
//                    .padding(top = 24.dp)
//                    .size(itemSize)
//                    .clip(CircleShape)// clip to the circle shape
//                    .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
//                contentScale = ContentScale.Crop
//            )
            Image(
                contentScale = ContentScale.Crop,// crop the image if it's not a square
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(itemSize)
                    .clip(CircleShape)// clip to the circle shape
                    .border(1.dp, MaterialTheme.colorScheme.primary, CircleShape),
                painter = painterResource(id = item.resId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                TextTitleMedium(text = "${item.price} T")
                Spacer(modifier = Modifier.weight(1f))
                TextTitleMedium(text = item.title)
            }
            if (selectVisibility) {
                Row(modifier = Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically) {

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

data class ProductListItemData(
    val id: Int,
    val title: String,
    val fee: String,
    val categoryId: Int,
    val resId: Int,
    val price: Int
)

data class SubCategoryItemData(var id: Int, var title: String, var isSelected: Boolean)

//@Preview(showBackground = false, showSystemUi = true)
//@Composable
//fun SimpleView() {
//    AppTheme {
//        Surface {
//            var x : (Int , String) -> Unit = { x , y -> x }
//            ProductGridItem(false,ProductListItemData(
//                1, "قهوه","75",1, R.drawable.coffee_cup
//            ),x)
//        }
//    }
//}