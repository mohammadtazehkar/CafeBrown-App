package com.example.cafebrown.ui.screens

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.cafebrown.R
import com.example.cafebrown.data.models.productDetail.GetCommentResponse
import com.example.cafebrown.ui.components.CardColumnMediumCorner
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleSmall

@Composable
fun CommentListDialog(
    commentsList: List<GetCommentResponse>,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        CardColumnMediumCorner() {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                content = {
                    item {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    items(commentsList.size) { index ->
                        CommentListItem(
                            item = commentsList[index],
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            )


        }
    }

}

@Composable
fun CommentListItem(
    item: GetCommentResponse
){
    Column {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            CommentItemStarsRow(
                modifier = Modifier.weight(1.0f),
                rate = item.rate,
            )
            TextTitleMedium(text = item.user)
        }
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl){
            Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.Start) {
                TextTitleSmall(text = item.text, textAlign = TextAlign.Justify)
            }
        }
        TextTitleMedium(modifier = Modifier.padding(top = 16.dp) ,text = item.dateTime, textAlign = TextAlign.Start)
        Divider()
    }
}

@Composable
fun CommentItemStarsRow(
    modifier: Modifier = Modifier,
    stars: MutableList<Boolean> = mutableListOf(false,false,false,false,false),
    rate: Int,
) {
    if (rate != 0){
        stars.forEachIndexed { index, _ ->
            stars[index] = index <= (rate-1)
        }
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        stars.forEach {status->
            Image(
                painter = painterResource(id =
                    if (status){
                        R.mipmap.ic_star_fill
                    }
                    else{
                        R.mipmap.ic_star_empty
                    }
                ) ,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}