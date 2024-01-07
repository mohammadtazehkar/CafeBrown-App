package com.example.cafebrown.presentation.events

import com.example.cafebrown.utils.UIText


sealed class AppUIEvent{
    data class ShowMessage(val message: UIText): AppUIEvent()
}
