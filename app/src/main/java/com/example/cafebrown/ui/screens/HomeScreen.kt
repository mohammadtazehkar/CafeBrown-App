package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.viewmodels.HomeViewModel
import com.example.cafebrown.ui.components.AppBannerPager
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.CardColumnMediumCorner
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.PrimaryButtonMainItemWithImage
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.theme.AppTheme
import com.example.cafebrown.utils.ClickHelper
import com.example.cafebrown.utils.Constants.NAV_INFO
import com.example.cafebrown.utils.Constants.NAV_MENU
import com.example.cafebrown.utils.Constants.NAV_PROFILE
import com.example.cafebrown.utils.Constants.NAV_RESERVE
import com.example.cafebrown.utils.Constants.NAV_TABLE
import com.example.cafebrown.utils.Constants.NAV_TRANSACTION

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
    onClickItem: (Int) -> Unit,

) {

    val context = LocalContext.current
    val homeState = homeViewModel.homeState.value
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.app_name)
            )
        })
    { paddingValues ->
        HomeContent(
            modifier = Modifier.padding(paddingValues),
            imageList = homeState.imageList,
            onClickItem
        )

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    imageList: List<String>,
    onClickItems: (Int) -> Unit
) {
    MainColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        AppBannerPager(
            modifier = Modifier.weight(0.3f),
            images = imageList
        )
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .weight(0.7f)
        ) {

            Row(modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Max)) {
                HomeItem(modifier = Modifier.weight(0.5f).fillMaxHeight(),imageId = R.mipmap.ic_menu, label = stringResource(id = R.string.menu),onClickItem = onClickItems, naveItem = NAV_MENU)
                Divider(modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .padding(vertical = 16.dp))
                HomeItem(modifier = Modifier.weight(0.5f).fillMaxHeight(),imageId = R.mipmap.ic_reserve, label = stringResource(id = R.string.desk_reserve_and_order),onClickItem = onClickItems, naveItem = NAV_TABLE)
            }
            Row(modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Max)) {
                HomeItem(modifier = Modifier.weight(0.5f).fillMaxHeight(),imageId = R.mipmap.ic_history, label = stringResource(id = R.string.reserve_history),onClickItem = onClickItems, naveItem = NAV_RESERVE)
                Divider(modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .padding(vertical = 16.dp))
                HomeItem(modifier = Modifier.weight(0.5f).fillMaxHeight(),imageId = R.mipmap.ic_transaction, label = stringResource(id = R.string.transaction),onClickItem = onClickItems, naveItem = NAV_TRANSACTION)
            }
            Row(modifier = Modifier
                .weight(1f)
                .height(IntrinsicSize.Max)) {
                HomeItem(modifier = Modifier.weight(0.5f).fillMaxHeight(),imageId = R.mipmap.ic_profile, label = stringResource(id = R.string.profile), false,onClickItems,NAV_PROFILE)
                Divider(modifier = Modifier
                    .width(1.dp)
                    .fillMaxHeight()
                    .padding(vertical = 16.dp))
                HomeItem(modifier = Modifier.weight(0.5f).fillMaxHeight(),imageId = R.mipmap.ic_info, label = stringResource(id = R.string.about_us), false,onClickItems,NAV_INFO)
            }
        }

    }
}

@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    imageId : Int,
    label : String,
    dividerVisibility: Boolean = true,
    onClickItem: (Int) -> Unit,
    naveItem : Int
) {
    val configuration = LocalConfiguration.current
    val itemSize = 0.23 * configuration.screenWidthDp.dp
    Box (
        modifier = modifier.clickable { ClickHelper.getInstance().clickOnce { onClickItem(naveItem) }},
    ){
        Column (
            modifier = Modifier.align(alignment = Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                modifier = Modifier
                    .size(itemSize)
                    .padding(top = 8.dp),
                painter = painterResource(id = imageId), contentDescription = "")
            TextTitleMedium(
                modifier = Modifier.padding(vertical = 8.dp),
                text = label)
        }

        if (dividerVisibility){
            Divider(
                modifier = Modifier.padding(horizontal = 16.dp)
                    .align(alignment = Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = false, showSystemUi = true)
@Composable
fun SimpleComposablePreview() {
    AppTheme {
        Surface {
            HomeScreen(onClickItem = {})
        }
    }
}