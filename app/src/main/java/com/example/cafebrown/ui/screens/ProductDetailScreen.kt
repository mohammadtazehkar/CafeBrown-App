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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.ProductDetailEvent
import com.example.cafebrown.presentation.viewmodels.ProductDetailViewModel
import com.example.cafebrown.ui.components.AppBannerPager
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.TextBodyMedium
import com.example.cafebrown.ui.components.TextBodySmall
import com.example.cafebrown.ui.components.TextTitleMediumPrimary

@Composable
fun ProductDetailScreen(
    productDetailViewModel: ProductDetailViewModel = viewModel(),
    onNavUp: () -> Unit
) {
    val productDetailState = productDetailViewModel.productDetailState.value


    Scaffold (
        topBar = {
            AppTopAppBar(title = productDetailState.productTitle,isBackVisible = true, onBack = onNavUp)
        },
        content = {paddingValues ->

            val configuration = LocalConfiguration.current
            val sliderHeight = 0.25 * configuration.screenHeightDp.dp

            MainColumn(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {

                ProductSlider(modifier = Modifier.height(sliderHeight),productDetailState.imageList)
                Spacer(modifier = Modifier.height(8.dp))
                ProductDetail(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    productStars = productDetailState.productStarsList,
                    userStars = productDetailState.userStarsList,
                    onStarsClick = {index,status ->
                        productDetailViewModel.onEvent(ProductDetailEvent.UpdateStars(index,status))
                    },
                    productDescription = productDetailState.productDescription,
                    productInstruction = productDetailState.productInstruction,
                    userComment = productDetailState.userComment,
                    onUserCommentChange = {newValue ->
                        productDetailViewModel.onEvent(ProductDetailEvent.UpdateUserComment(newValue))
                    }
                )
            }
        }
    )
}

@Composable
fun ProductSlider(
    modifier: Modifier,
    imageList: List<String>,
){
    Column(modifier = modifier) {
//        AppBannerPager(
//            images = imageList
//        )
    }
}

@Composable
fun ProductDetail(
    modifier: Modifier = Modifier,
    productStars: MutableList<MutableState<Boolean>>,
    userStars: MutableList<MutableState<Boolean>>,
    onStarsClick: (Int, Boolean) -> Unit,
    productDescription: String,
    productInstruction: String,
    userComment: String,
    onUserCommentChange: (String) -> Unit
){
    Column(modifier = modifier) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            TextTitleMediumPrimary(modifier = Modifier.fillMaxWidth(),text = stringResource(id = R.string.detail_of_product), textAlign = TextAlign.Start)
        }
        Spacer(modifier = Modifier.height(8.dp))
        CommentAndStarsRow(modifier = Modifier.fillMaxWidth(),productStars,stringResource(id = R.string.see_commend),false,onStarsClick)
        Spacer(modifier = Modifier.height(8.dp))
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
//            TextBodyMedium(modifier = Modifier.fillMaxWidth(),text = productDescription, textAlign = TextAlign.Justify)
            TextBodyMedium(modifier = Modifier.fillMaxWidth(),text = stringResource(id = R.string.lorem_ipsum), textAlign = TextAlign.Justify)
            Spacer(modifier = Modifier.height(8.dp))
            TextTitleMediumPrimary(modifier = Modifier.fillMaxWidth(),text = stringResource(id = R.string.instructions), textAlign = TextAlign.Start)
//            TextBodyMedium(modifier = Modifier.fillMaxWidth(),text = productInstruction, textAlign = TextAlign.Justify)
            TextBodyMedium(modifier = Modifier.fillMaxWidth(),text = stringResource(id = R.string.lorem_ipsum), textAlign = TextAlign.Justify)
            Spacer(modifier = Modifier.height(8.dp))
            TextTitleMediumPrimary(modifier = Modifier.fillMaxWidth(),text = stringResource(id = R.string.send_comment_and_rate), textAlign = TextAlign.Start)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(136.dp),
                value = userComment,
                onValueChange = onUserCommentChange,
                placeholder = {
                    TextBodyMedium(
                        text = stringResource(id = R.string.please_enter_your_comment),
                        textAlign = TextAlign.Justify
                    )
                }
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        CommentAndStarsRow(modifier = Modifier.fillMaxWidth(),userStars,stringResource(id = R.string.send_comment_and_rate),true,onStarsClick)
        Spacer(modifier = Modifier.height(16.dp))
    }
}
@Composable
fun CommentAndStarsRow(
    modifier: Modifier,
    stars: MutableList<MutableState<Boolean>>,
    actionLabel: String,
    starsClickable: Boolean,
    onStarsClick: (Int, Boolean) -> Unit
){
    Row (modifier = modifier, verticalAlignment = Alignment.CenterVertically){
        StarsRow(
            modifier = Modifier.weight(1f),
            stars = stars,
            clickable = starsClickable,
            onClick = onStarsClick
        )
        PrimaryButton(modifier = Modifier,text = actionLabel, onClick ={} )
    }
}


@Composable
fun StarsRow(
    modifier: Modifier = Modifier,
    clickable: Boolean,
    stars: MutableList<MutableState<Boolean>>,
    onClick: (Int, Boolean) -> Unit
) {

    val imagePainter1: Painter = painterResource(id = R.mipmap.ic_star_fill)
    val imagePainter2: Painter = painterResource(id = R.mipmap.ic_star_empty)
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        stars.forEachIndexed { index, status ->
            val transition = updateTransition(targetState = status, label = "imageTransition")
            val rotation by transition.animateFloat(label = "rotation") { isImage ->
                if (isImage.value) 0f else 360f
            }
            Image(
                painter = if (status.value) imagePainter1 else imagePainter2,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
                    .rotate(rotation)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                    ) {
                        if (clickable) {onClick(index, !status.value)}
                    }
            )
        }
    }
}