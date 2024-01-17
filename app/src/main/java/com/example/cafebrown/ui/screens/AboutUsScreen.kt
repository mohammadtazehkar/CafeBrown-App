package com.example.cafebrown.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.cafebrown.R
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainBox
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextTitleLarge
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.theme.AppTheme
import com.example.cafebrown.utils.ClickHelper

@Composable
fun AboutUsScreen(onNavUp: () -> Unit) {
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = stringResource(id = R.string.about_us), isBackVisible = true,
                onBack = onNavUp
            )
        }
    ) {
        MainColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top
        ) {
            TextTitleMedium(text = stringResource(id = R.string.about_cafe))
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                TextTitleMedium(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.lorem_ipsum),
                    textAlign = TextAlign.Justify
                )
                Divider(modifier = Modifier.padding(top = 8.dp), thickness = 3.dp)
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.mipmap.ic_address_brown),
                        contentDescription = "Location icon"
                    )
                    TextTitleMedium(
                        modifier = Modifier
                            .weight(2.0F)
                            .padding(top = 8.dp, start = 8.dp),
                        text = stringResource(id = R.string.coffee_address),
                        textAlign = TextAlign.Start
                    )
                }
                Divider(modifier = Modifier.padding(top = 8.dp), thickness = 3.dp)
                TextTitleMedium(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.connection_and_support)
                )
                TextTitleMedium(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.for_support_you_can_use_this_links),
                    textAlign = TextAlign.Justify
                )
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                IconButton(onClick = {
                    ClickHelper.getInstance().clickOnce {
                        // onClick()
                    }
                }) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.mipmap.ic_phone),
                        contentDescription = "phone icon"
                    )
                }
                IconButton(onClick = {
                    ClickHelper.getInstance().clickOnce {
                        // onClick()
                    }
                }) {
                    Image(
                        modifier = Modifier
                            .padding(start = 4.dp, end = 4.dp)
                            .size(32.dp),
                        painter = painterResource(id = R.mipmap.ic_telegram),
                        contentDescription = "telegram icon"
                    )
                }
                IconButton(onClick = {
                    ClickHelper.getInstance().clickOnce {
                        // onClick()
                    }
                }) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.mipmap.ic_instagram),
                        contentDescription = "instagram icon"
                    )
                }
            }

            TextButton(onClick = {
                ClickHelper.getInstance().clickOnce {
                    // onClick()
                }
            }) {
                TextTitleMedium(
                    modifier = Modifier.padding(top = 8.dp),
                    text = stringResource(id = R.string.submit_a_complaint),
                    textAlign = TextAlign.Center
                )
            }
            Divider(modifier = Modifier.padding(top = 8.dp), thickness = 3.dp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Column(modifier = Modifier.padding(end = 8.dp)) {
                    TextTitleMedium(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "ssid",
                        textAlign = TextAlign.Center
                    )
                    TextTitleMedium(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "password",
                        textAlign = TextAlign.Center
                    )
                }
                Image(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(id = R.mipmap.ic_wifi),
                    contentDescription = "WIFI icon"
                )
            }
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.mipmap.ic_hummer_brown),
                        contentDescription = "Human Rules icon"
                    )
                    TextButton(onClick = {
                        ClickHelper.getInstance().clickOnce {
                            // onClick()
                        }
                    }) {
                        TextTitleMedium(
                            modifier = Modifier
                                .weight(2.0F)
                                .padding(top = 8.dp),
                            text = stringResource(id = R.string.see_app_rules),
                            textAlign = TextAlign.Start
                        )
                    }
                }


                TextTitleLarge(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = stringResource(id = R.string.design_and_developed),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "company logo"
                )
                TextTitleMedium(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = stringResource(id = R.string.if_you_follow_quality),
                    textAlign = TextAlign.Center
                )
                TextTitleMedium(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = stringResource(id = R.string.we_are_ready_for_this),
                    textAlign = TextAlign.Center
                )
                TextTitleMedium(
                    modifier = Modifier
                        .padding(top = 8.dp),
                    text = stringResource(id = R.string.because_of_make_that_is_for_us),
                    textAlign = TextAlign.Center
                )
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Row(
                        modifier = Modifier.padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = R.mipmap.ic_phone),
                            contentDescription = "phone icon"
                        )
                        TextTitleMedium(
                            modifier = Modifier
                                .padding(top = 8.dp),
                            text = stringResource(id = R.string.company_phone),
                        )
                    }
                }
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.mipmap.ic_web),
                        contentDescription = "phone icon"
                    )
                    TextTitleMedium(
                        modifier = Modifier
                            .padding(top = 8.dp),
                        text = stringResource(id = R.string.company_website),
                    )
                }


            }

        }
    }


}

@Preview(showBackground = true)
@Composable
fun previewAboutUs() {
    AppTheme {
        AboutUsScreen({})
    }
}