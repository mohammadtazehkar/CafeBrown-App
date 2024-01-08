package com.example.cafebrown.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cafebrown.ui.components.Picker
import com.example.cafebrown.ui.components.rememberPickerState

@Composable
fun ProfileScreen(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        val years = remember { (1300..1402).map { it.toString() } }
        val yearsPickerState = rememberPickerState()
        val units = remember { listOf("seconds", "minutes", "hours") }
        val unitsPickerState = rememberPickerState()
        val days = remember { (1..31).map { it.toString() } }
        val daysPickerState = rememberPickerState()

        Text(text = "Example Picker", modifier = Modifier.padding(top = 16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            Picker(
                state = yearsPickerState,
                items = years,
                visibleItemsCount = 3,
                modifier = Modifier.weight(0.3f),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 32.sp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Picker(
                state = unitsPickerState,
                items = units,
                visibleItemsCount = 3,
                modifier = Modifier.weight(0.7f),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 32.sp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Picker(
                state = daysPickerState,
                items = days,
                visibleItemsCount = 3,
                modifier = Modifier.weight(0.3f),
                textModifier = Modifier.padding(8.dp),
                textStyle = TextStyle(fontSize = 32.sp)
            )
        }

        Text(
            text = "Interval: ${yearsPickerState.selectedItem} ${unitsPickerState.selectedItem} ${daysPickerState.selectedItem}" ,
            modifier = Modifier.padding(vertical = 16.dp)
        )

    }
}