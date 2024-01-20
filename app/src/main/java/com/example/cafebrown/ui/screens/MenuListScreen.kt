package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.viewmodels.MenuListViewModel
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.utils.ClickHelper

@Composable
fun MenuListScreen(
    menuListViewModel: MenuListViewModel = viewModel(),
    onNavigateToProductList: (Int,String) -> Unit,
    onNavUp: () -> Unit,
) {
    val menuState = menuListViewModel.menuState.value
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.menu),
                isBackVisible = true,
                onBack = onNavUp
            )
        },
        content = { paddingValues ->
            MainColumn(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
//                if (categoryList.isNotEmpty()) {
                MenuGrid(
                    items = menuState.menuListState,
                    onItemClick = {
                        onNavigateToProductList(it, menuState.from)
                    }
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
fun MenuGrid(
    items: List<MenuItemData>,
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
    item: MenuItemData,
    onItemClick: (Int) -> Unit
) {
    val configuration = LocalConfiguration.current
    val itemSize = 0.23 * configuration.screenWidthDp.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                ClickHelper.getInstance().clickOnce { onItemClick(item.id) }
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
//                modifier = Modifier.padding(top = 24.dp)
//                    .size(itemSize),
//            )
            Image(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .size(itemSize),
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


data class MenuItemData(val id: Int, val title: String, val resId: Int)