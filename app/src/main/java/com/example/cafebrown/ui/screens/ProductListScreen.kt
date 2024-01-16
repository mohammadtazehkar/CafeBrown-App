package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.ProductListEvent
import com.example.cafebrown.presentation.viewmodels.ProductListViewModel
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.ui.components.TextTitleSmallPrimary
import com.example.cafebrown.utils.ClickHelper

@Composable
fun ProductListScreen(
    productListViewModel: ProductListViewModel = viewModel(),
    onNavigateToDetail: (Int,String)->Unit,
    onNavUp: ()->Unit,
){
    val productState = productListViewModel.productListState
    Scaffold (
        topBar = {
            AppTopAppBar(title = stringResource(id = R.string.products),isBackVisible = true, onBack = onNavUp)
        },
        content = {paddingValues ->
            MainColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                SubCategoryList(
                    items = productState.value.subCategoryListState,
                    onItemClick = {subCategoryId ->
                        productListViewModel.onEvent(ProductListEvent.SelectSubCategory(subCategoryId))
                    }
                )
//                if (categoryList.isNotEmpty()) {
                ProductGrid(
                        items = productState.value.productListState,
                        onItemClick = onNavigateToDetail
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
){
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            item{
                Spacer(modifier = Modifier.width(1.dp))
            }
            items(items.size){index ->
                SubCategoryListItem(item = items[index], onItemClick = onItemClick)
            }
            item{
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
        Column (
            modifier = Modifier
                .clip(MaterialTheme.shapes.small)// clip to the circle shape
                .border(
                    1.dp,
                    if (item.isSelected){MaterialTheme.colorScheme.primary}else{MaterialTheme.colorScheme.onPrimaryContainer},
                    CircleShape
                )
                .clickable {
                    ClickHelper
                        .getInstance()
                        .clickOnce { onItemClick(item.id) }
                }
        ){
                if (item.isSelected){
                    TextTitleSmallPrimary(modifier = Modifier.padding(horizontal = 16.dp,vertical = 4.dp),text = item.title)
                }else{
                    TextTitleSmall(modifier = Modifier.padding(horizontal = 16.dp,vertical = 4.dp),text = item.title)
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
    items : List<ProductListItemData>,
    onItemClick : (Int,String) -> Unit
){
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(count = 2),
    ) {
        items(count = items.size) { index ->
            ProductGridItem(
                isOdd = index % 2 != 0,
                item = items[index],
                onItemClick = onItemClick
            )
        }
    }
}
@Composable
fun ProductGridItem(
    isOdd: Boolean,
    item: ProductListItemData,
    onItemClick: (Int,String) -> Unit
) {
    val configuration = LocalConfiguration.current
    val itemSize = 0.23 * configuration.screenWidthDp.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                ClickHelper
                    .getInstance()
                    .clickOnce { onItemClick(item.id,item.title) }
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

data class ProductListItemData(val id : Int, val title: String, val fee: String,val categoryId: Int, val resId : Int)
data class SubCategoryItemData(var id : Int, var title: String, var isSelected: Boolean)