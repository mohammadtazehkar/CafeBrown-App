package com.example.cafebrown.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AppDatePicker(
    width: Dp = 64.dp ,
    yearList: List<String>,
    monthList: List<String>,
    dayList: List<String>,
    onSelectedYear: (String) -> Unit,
    onSelectedMonth: (String) -> Unit,
    onSelectedDay: (String) -> Unit,
){
    Row() {
        AppNumberPicker(
            items = yearList,
            visibleItemsCount = 3,
            onSelect = onSelectedYear
        )
        Spacer(modifier = Modifier.size(16.dp))
        AppNumberPicker(
            items = monthList,
            visibleItemsCount = 3,
            onSelect = onSelectedMonth
        )
        Spacer(modifier = Modifier.size(16.dp))
        AppNumberPicker(
            width = width,
            items = dayList,
            visibleItemsCount = 3,
            onSelect = onSelectedDay
        )
    }

}