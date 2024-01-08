package com.example.cafebrown.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cafebrown.utils.ClickHelper


@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = { ClickHelper.getInstance().clickOnce { onClick() }},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small
    ) {
        TextTitleSmallOnPrimary(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
){
    Button(
        modifier = modifier.padding(16.dp),
        onClick = { ClickHelper.getInstance().clickOnce { onClick() }},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small
    ) {
        TextTitleSmallOnPrimary(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier,
    firstText: String,
    secondText: String,
    status: Boolean,
    onClick: () -> Unit
){
    Button(
        modifier = modifier
            .padding(16.dp),
        onClick = { ClickHelper.getInstance().clickOnce { onClick() }},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.small
    ) {
        if (status) {
            TextTitleSmallOnPrimary(
                modifier = Modifier.padding(vertical = 8.dp),
                text = firstText,
                fontWeight = FontWeight.Bold
            )
        }
        else{
            TextTitleSmallOnPrimary(
                modifier = Modifier.padding(vertical = 8.dp),
                text = secondText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit
){
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        onClick = { ClickHelper.getInstance().clickOnce { onClick() }},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        shape = MaterialTheme.shapes.small
    ) {
        TextTitleSmallOnSecondary(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
){
    Button(
        modifier = modifier.padding(16.dp),
        onClick = { ClickHelper.getInstance().clickOnce { onClick() }},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        shape = MaterialTheme.shapes.small
    ) {
        TextTitleSmallOnSecondary(
            modifier = Modifier.padding(vertical = 8.dp),
            text = text,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun SecondaryButton(
    modifier: Modifier,
    firstText: String,
    secondText: String,
    status: Boolean,
    onClick: () -> Unit
){
    Button(
        modifier = modifier
            .padding(16.dp),
        onClick = { ClickHelper.getInstance().clickOnce { onClick() }},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        shape = MaterialTheme.shapes.small
    ) {
        if (status) {
            TextTitleSmallOnSecondary(
                modifier = Modifier.padding(vertical = 8.dp),
                text = firstText,
                fontWeight = FontWeight.Bold
            )
        }
        else{
            TextTitleSmallOnSecondary(
                modifier = Modifier.padding(vertical = 8.dp),
                text = secondText,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun PrimaryButtonExtraSmallCorner(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
){
    Button(
        onClick = { ClickHelper.getInstance().clickOnce { onClick() }},
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        TextTitleSmallOnPrimary(
            text = text,
            modifier = Modifier.padding(vertical = 4.dp),
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PrimaryOutlinedButtonExtraSmallCorner(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
){
    OutlinedButton(
        onClick = { ClickHelper.getInstance().clickOnce { onClick() }},
        modifier = modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.extraSmall,
    ) {
        TextTitleSmallPrimary(
            text = text,
            modifier = Modifier.padding(vertical = 4.dp),
            fontWeight = FontWeight.Bold
        )
    }
}