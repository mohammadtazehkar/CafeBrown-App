package com.example.cafebrown.ui.screens

import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.cafebrown.R
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleSmall
import com.example.cafebrown.ui.theme.AppTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun TransactionScreen(onNavUp: () -> Unit) {
//    val context = LocalContext.current.getActivity()!!
    var trData = mutableListOf(
        TransactionItemData(
            R.mipmap.ic_credit_card,
            "mamali",
            "2000",
            "خرید سفارش",
            "موفق",
            "1402/10/25 - 18:30"
        ),
        TransactionItemData(
            R.mipmap.ic_wallet,
            "شکر",
            "1000",
            "افزایش اعتبار",
            "موفق",
            "1402/10/25 - 18:30"
        ),
        TransactionItemData(
            R.mipmap.ic_credit_card,
            "شکر",
            "1000",
            "افزایش اعتبار",
            "موفق",
            "1402/10/25 - 18:30"
        ),
        TransactionItemData(
            R.mipmap.ic_wallet,
            "شکر",
            "1000",
            "افزایش اعتبار",
            "موفق",
            "1402/10/25 - 18:30"
        ),
        TransactionItemData(
            R.mipmap.ic_credit_card,
            "شکر",
            "1000",
            "افزایش اعتبار",
            "موفق",
            "1402/10/25 - 18:30"
        ),
        TransactionItemData(
            R.mipmap.ic_wallet,
            "شکر",
            "1000",
            "افزایش اعتبار",
            "موفق",
            "1402/10/25 - 18:30"
        )
    )

    Scaffold(topBar = {
        AppTopAppBar(
            title = stringResource(id = R.string.transaction),
            isBackVisible = true,
            onBack = onNavUp
        )
    }, bottomBar = { transactionBottomBar("27000") }) {
        MainBox {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(it), columns = GridCells.Fixed(2)
            ) {
                items(count = trData.size) { index ->
                    TransactionGridItem(
                        transactionData = trData[index],
                        shouldShowDivider = index % 2 == 0
                    )
                }
            }
        }

    }

}

@Composable
fun TransactionGridItem(
    modifier: Modifier = Modifier,
    transactionData: TransactionItemData,
    shouldShowDivider: Boolean
) {
    Box(modifier = modifier.height(IntrinsicSize.Max)) {
        if (shouldShowDivider)
            Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxHeight()
                    .width(1.dp)
                    .align(Alignment.TopEnd)
            )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .size(64.dp),
                painter = painterResource(id = transactionData.image),
                contentDescription = transactionData.imageName
            )
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
            {
                TextTitleMedium(
                    text = "${transactionData.price} ${stringResource(id = R.string.toman)}",
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
            TextTitleSmall(text = transactionData.type)
            Row(modifier = Modifier.padding(vertical = 4.dp)) {
                TextTitleSmall(
                    text = transactionData.transactionDate,
                    modifier = Modifier
                        .weight(2.0F)
                        .padding(start = 12.dp),
                    textAlign = TextAlign.Left
                )
                TextTitleSmall(
                    text = transactionData.status,
                    modifier = Modifier.padding(end = 10.dp)
                )
            }
            Divider(modifier = Modifier.padding(horizontal = 8.dp))
        }

    }
}

@Composable
fun transactionBottomBar(price: String) {
    Row(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
        {
            TextTitleMedium(
                text = "${price} ${stringResource(id = R.string.toman)}",
                modifier = Modifier
                    .weight(2.0F)
                    .padding(8.dp),
                textAlign = TextAlign.End
            )
            TextTitleSmall(text = stringResource(id = R.string.your_balance_is) , modifier = Modifier.padding(start = 4.dp))
            Image(
                painter = painterResource(id = R.mipmap.ic_add_brown),
                contentDescription = "Add",
                modifier = Modifier
                    .size(32.dp)
                    .padding(start = 4.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun preview() {
    AppTheme {
        TransactionScreen({ })
//        var trData = mutableListOf(
//            TransactionItemData(
//                R.drawable.logo,
//                "mamali",
//                "2000",
//                "خرید سفارش",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            ),
//            TransactionItemData(
//                R.drawable.logo,
//                "شکر",
//                "1000",
//                "افزایش اعتبار",
//                "موفق",
//                "1402/10/25 - 18:30"
//            )
//        )
//        trData.forEachIndexed { index, item ->
//            TransactionGridItem(
//                transactionData = item,
//                shouldShowDivider = index % 2 == 0
//            )
//        }
    }
}

fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

data class TransactionItemData(
    val image: Int,
    val imageName: String,
    val price: String,
    val type: String,
    val status: String,
    val transactionDate: String
)