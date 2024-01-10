package com.example.cafebrown.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cafebrown.R
import com.example.cafebrown.utils.ClickHelper

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopAppBar(
    title: String,
    isBackVisible: Boolean = false,
    onBack: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        CenterAlignedTopAppBar(
            title = {
                TextTitleMedium(text = title)
            },
            navigationIcon = {
                if (isBackVisible) {
                    IconButton(onClick ={ ClickHelper.getInstance().clickOnce { onBack() }}) {
                        Image(
                            painter = painterResource(id = R.mipmap.ic_back_white),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        )
    }
}