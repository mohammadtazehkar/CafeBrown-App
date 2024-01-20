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
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.viewmodels.ReserveHistoryViewModel
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextLabelSmallOnPrimary
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleMediumError

@Composable
fun ReserveHistoryScreen(
    reserveHistoryViewModel: ReserveHistoryViewModel = viewModel(),
    onNavigateToReserve: () -> Unit,
    onNavUp: () -> Unit
) {
    val reserveState = reserveHistoryViewModel.reserveHistoryState.value
    Scaffold (
        topBar = {
            AppTopAppBar(title = stringResource(id = R.string.reserve_history),true,onNavUp)
        },
        content = {
            MainColumn (
                modifier = Modifier.padding(it),
                verticalArrangement = Arrangement.Top
            ){
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    content = {
                        items(reserveState.reserveList.size) { index ->
                            ReserveGridItem(
                                index % 2 != 0,
                                deckNum = reserveState.reserveList[index].deckNum,
                                date = reserveState.reserveList[index].date,
                                status = reserveState.reserveList[index].status,
                                onItemClick = onNavigateToReserve
                            )
                        }
                    })
            }
        }
    )
}
@Composable
fun ReserveGridItem(
    isOdd: Boolean,
    date: String,
    deckNum: String,
    status: Boolean,
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
                        .padding(vertical = 16.dp)
                        .size(64.dp),
                    painter = painterResource(id = R.mipmap.ic_desk), contentDescription = ""
                )
                TextLabelSmallOnPrimary(
                    modifier = Modifier.align(Alignment.Center),
                    text = deckNum
                )
            }
                if (status){
                    TextTitleMedium(text = date)
                }else{
                    TextTitleMediumError(text = date)
                }

            Spacer(modifier = Modifier.height(16.dp))
            Divider(modifier = Modifier.padding(horizontal = 16.dp))
        }
    }
}
data class ReserveItemData(
    val id: Int,
    val date: String,
    val deckNum: String,
    val status: Boolean
)