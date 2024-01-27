package com.example.cafebrown.ui.screens


import android.util.Log
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
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AppUIEvent
import com.example.cafebrown.presentation.events.ProductDetailEvent
import com.example.cafebrown.presentation.events.ProductListEvent
import com.example.cafebrown.presentation.viewmodels.ProductDetailViewModel
import com.example.cafebrown.ui.components.AppBannerPager
import com.example.cafebrown.ui.components.AppSnackBar
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.PrimaryButton
import com.example.cafebrown.ui.components.ProgressBarDialog
import com.example.cafebrown.ui.components.TextBodyMedium
import com.example.cafebrown.ui.components.TextBodySmall
import com.example.cafebrown.ui.components.TextTitleMediumPrimary
import com.example.cafebrown.utils.UIText
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailScreen(
    productDetailViewModel: ProductDetailViewModel = hiltViewModel(),
    onNavUp: () -> Unit,
    onExpiredToken: () -> Unit
) {
    val productDetailState = productDetailViewModel.productDetailState.value
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        Log.i("mamali","firstLine of LaunchedEffect uiFlow is : ${productDetailViewModel.uiEventFlow}")
        productDetailViewModel.uiEventFlow.collect { event ->
            when (event) {
                is AppUIEvent.ShowMessage -> {
                    if (event.needAction) {
                        val result = snackBarHostState
                            .showSnackbar(
                                message = event.message.asString(context),
                                actionLabel = UIText.StringResource(R.string.try_again)
                                    .asString(context),
                                duration = SnackbarDuration.Indefinite
                            )
                        when (result) {
                            SnackbarResult.ActionPerformed -> {
                                productDetailViewModel.onEvent(ProductDetailEvent.GetDataFromServer)
                            }

                            else -> {}
                        }
                    }
                    else {
                        snackBarHostState.showSnackbar(message = event.message.asString(context))
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
    DisposableEffect(Unit) {
        if (!productDetailState.hasRunEffect) {
            // Run your code here that you want to execute only once
            productDetailViewModel.onEvent(ProductDetailEvent.UpdateHasRunEffect(true))
            productDetailViewModel.onEvent(ProductDetailEvent.GetDataFromServer)
        }

        onDispose {
            // Cleanup code, if needed
        }
    }

    if (productDetailState.isLoading) {
        ProgressBarDialog()
    }
    if (productDetailState.commentListDialogVisibility) {
        CommentListDialog(
            commentsList = productDetailState.commentList,
            onDismiss = {
                productDetailViewModel.onEvent(ProductDetailEvent.UpdateCommentDialogVisibility(false))
            }
        )
    }
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = productDetailState.productTitle,
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

            val configuration = LocalConfiguration.current
            val sliderHeight = 0.25 * configuration.screenHeightDp.dp

            MainColumn(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
            ) {

                ProductSlider(
                    modifier = Modifier.height(sliderHeight),
                    productDetailState.imageList
                )
                Spacer(modifier = Modifier.height(8.dp))
                ProductDetail(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    productStars = productDetailState.productStarsList,
                    userStars = productDetailState.userStarsList,
                    onStarsClick = { index, status ->
                        productDetailViewModel.onEvent(
                            ProductDetailEvent.UpdateStars(
                                index,
                                status
                            )
                        )
                    },
                    productDescription = productDetailState.productDescription,
                    productInstruction = productDetailState.productInstruction,
                    userComment = productDetailState.userComment,
                    onUserCommentChange = { newValue ->
                        productDetailViewModel.onEvent(ProductDetailEvent.UpdateUserComment(newValue))
                    },
                    onShowComment = {
                        productDetailViewModel.onEvent(ProductDetailEvent.GetCommentListFromServer)
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
) {
    Column(modifier = modifier) {
        AppBannerPager(
            stringImages = imageList
        )
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
    onUserCommentChange: (String) -> Unit,
    onShowComment: () -> Unit
) {
    Column(modifier = modifier) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            TextTitleMediumPrimary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.detail_of_product),
                textAlign = TextAlign.Start
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        CommentAndStarsRow(
            modifier = Modifier.fillMaxWidth(),
            productStars,
            stringResource(id = R.string.see_commend),
            false,
            onStarsClick,
            onShowComment
        )
        Spacer(modifier = Modifier.height(8.dp))
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            TextBodyMedium(
                modifier = Modifier.fillMaxWidth(),
                text = productDescription,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextTitleMediumPrimary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.instructions),
                textAlign = TextAlign.Start
            )
            TextBodyMedium(
                modifier = Modifier.fillMaxWidth(),
                text = productInstruction,
                textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextTitleMediumPrimary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.send_comment_and_rate),
                textAlign = TextAlign.Start
            )
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
        CommentAndStarsRow(
            modifier = Modifier.fillMaxWidth(),
            userStars,
            stringResource(id = R.string.send_comment_and_rate),
            true,
            onStarsClick,
            {/*TODO*/}
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CommentAndStarsRow(
    modifier: Modifier,
    stars: MutableList<MutableState<Boolean>>,
    actionLabel: String,
    starsClickable: Boolean,
    onStarsClick: (Int, Boolean) -> Unit,
    onActionClick: () -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        StarsRow(
            modifier = Modifier.weight(1f),
            stars = stars,
            clickable = starsClickable,
            onClick = onStarsClick
        )
        PrimaryButton(modifier = Modifier, text = actionLabel, onClick = onActionClick)
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
                        if (clickable) {
                            onClick(index, !status.value)
                        }
                    }
            )
        }
    }
}