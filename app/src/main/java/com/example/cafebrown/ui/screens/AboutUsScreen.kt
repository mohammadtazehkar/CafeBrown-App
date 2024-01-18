package com.example.cafebrown.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cafebrown.R
import com.example.cafebrown.presentation.events.AboutUsEvent
import com.example.cafebrown.presentation.viewmodels.AboutUsViewModel
import com.example.cafebrown.ui.components.AppTopAppBar
import com.example.cafebrown.ui.components.MainColumn
import com.example.cafebrown.ui.components.TextTitleLarge
import com.example.cafebrown.ui.components.TextTitleMedium
import com.example.cafebrown.ui.components.TextTitleMediumPrimary
import com.example.cafebrown.ui.theme.AppTheme
import com.example.cafebrown.utils.ClickHelper

@Composable
fun AboutUsScreen(aboutUsViewModel: AboutUsViewModel = viewModel(), onNavUp: () -> Unit) {
    val aboutUsState = aboutUsViewModel.aboutUsState.value
    if (aboutUsState.rulesDialogVisibility) {
        RulesDialog(onDismissRequest = {
            aboutUsViewModel.onEvent(
                AboutUsEvent.ChangeRulesDialogVisibility(
                    false
                )
            )
        }, isFromAboutUs = true)
    }
    if (aboutUsState.complaintDialogVisibility) {
        ComplaintDialog(
            onDismissRequest = {
                aboutUsViewModel.onEvent(
                    AboutUsEvent.ChangeComplaintDialogVisibility(
                        false
                    )
                )
            },
            onConfirmation = {
                aboutUsViewModel.onEvent(
                    AboutUsEvent.ChangeComplaintDialogVisibility(
                        false
                    )
                )
            },
            onChangeComplaint = { aboutUsViewModel.onEvent(AboutUsEvent.ChangeComplaintData(it)) },
            complaintText = aboutUsState.complaintData
        )
    }
    Scaffold(topBar = {
        AppTopAppBar(
            title = stringResource(id = R.string.about_us),
            isBackVisible = true,
            onBack = onNavUp
        )
    }) {
        MainColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Top
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                AboutCafe(aboutUsState.aboutCafe.asString())
                Divider(modifier = Modifier.padding(top = 8.dp), thickness = 3.dp)
                CafeAddress(aboutUsState.cafeAddress.asString())
                Divider(modifier = Modifier.padding(top = 8.dp), thickness = 3.dp)
                SupportCafe(
                    aboutUsState.cafePhone, aboutUsState.cafeTelegram, aboutUsState.cafeInstagram
                )
                TextButton(onClick = {
                    ClickHelper.getInstance().clickOnce {
                        aboutUsViewModel.onEvent(AboutUsEvent.ChangeComplaintDialogVisibility(true))
                    }
                }) {
                    TextTitleMedium(
                        modifier = Modifier.padding(top = 8.dp),
                        text = stringResource(id = R.string.submit_a_complaint),
                        textAlign = TextAlign.Center
                    )
                }
                Divider(modifier = Modifier.padding(top = 8.dp), thickness = 3.dp)
                CafeWifi(aboutUsState.cafeWifiSSID, aboutUsState.cafeWifiPassword)
                CafeRules {
                    aboutUsViewModel.onEvent(AboutUsEvent.ChangeRulesDialogVisibility(true))
                }
                AboutCompany()
            }
        }
    }
}

@Composable
fun AboutCafe(aboutCafe: String) {
    TextTitleMediumPrimary(text = stringResource(id = R.string.about_cafe))
    TextTitleMedium(
        modifier = Modifier.padding(top = 8.dp), text = aboutCafe, textAlign = TextAlign.Justify
    )

}

@Composable
fun CafeAddress(cafeAddress: String) {
    Row(
        modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically
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
            text = cafeAddress,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun SupportCafe(cafePhone: String, cafeTelegram: String, cafeInstagram: String) {
    val context = LocalContext.current
    TextTitleMedium(
        modifier = Modifier.padding(top = 8.dp),
        text = stringResource(id = R.string.connection_and_support)
    )
    TextTitleMedium(
        modifier = Modifier.padding(top = 8.dp),
        text = stringResource(id = R.string.for_support_you_can_use_this_links),
        textAlign = TextAlign.Justify
    )
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        IconButton(onClick = {
            ClickHelper.getInstance().clickOnce {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(cafeInstagram)
                context.startActivity(intent)
            }
        }) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.mipmap.ic_instagram),
                contentDescription = cafeInstagram
            )
        }
        IconButton(onClick = {
            ClickHelper.getInstance().clickOnce {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(cafeTelegram)
                context.startActivity(intent)
            }
        }) {
            Image(
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp)
                    .size(32.dp),
                painter = painterResource(id = R.mipmap.ic_telegram),
                contentDescription = cafeTelegram
            )
        }
        IconButton(onClick = {
            ClickHelper.getInstance().clickOnce {
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:$cafePhone")
                context.startActivity(dialIntent)
            }
        }) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.mipmap.ic_phone),
                contentDescription = cafePhone
            )
        }
    }
}

@Composable
fun CafeWifi(cafeWifiSSID: String, cafeWifiPassword: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.mipmap.ic_wifi),
            contentDescription = "WIFI icon"
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            TextTitleMedium(
                modifier = Modifier.padding(top = 8.dp),
                text = cafeWifiSSID,
                textAlign = TextAlign.Center
            )
            TextTitleMedium(
                modifier = Modifier.padding(top = 8.dp),
                text = cafeWifiPassword,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun CafeRules(onClick: () -> Unit) {
    Row(
        modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.mipmap.ic_hummer_brown),
            contentDescription = "Human Rules icon"
        )
        TextButton(onClick = {
            ClickHelper.getInstance().clickOnce {
                onClick()
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
}

@Composable
fun AboutCompany() {
    val configuration = LocalConfiguration.current
    val logoWidth = 0.4 * configuration.screenWidthDp.dp
    val logoHeight = 0.46 * 0.4 * configuration.screenWidthDp.dp

    TextTitleLarge(
        modifier = Modifier.padding(top = 8.dp),
        text = stringResource(id = R.string.design_and_developed),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
    Image(
        modifier = Modifier
            .padding(4.dp)
            .width(logoWidth)
            .height(logoHeight),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "company logo"
    )
    TextTitleMedium(
        modifier = Modifier.padding(top = 8.dp),
        text = stringResource(id = R.string.if_you_follow_quality),
        textAlign = TextAlign.Center
    )
    TextTitleMedium(
        modifier = Modifier.padding(top = 8.dp),
        text = stringResource(id = R.string.we_are_ready_for_this),
        textAlign = TextAlign.Center
    )
    TextTitleMedium(
        modifier = Modifier.padding(top = 8.dp),
        text = stringResource(id = R.string.because_of_make_that_is_for_us),
        textAlign = TextAlign.Center
    )
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Row(
            modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = R.mipmap.ic_phone),
                contentDescription = "phone icon"
            )
            TextTitleMedium(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.company_phone),
            )
        }
    }
    Row(
        modifier = Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.mipmap.ic_web),
            contentDescription = "phone icon"
        )
        TextTitleMedium(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(id = R.string.company_website),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun previewAboutUs() {
    AppTheme {
        AboutUsScreen(viewModel(), {})
    }
}