package com.zyziek.calculator.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/*
    Modifier - let's us decorate and add behaviour to elements (background, padding, font etc)
 */
@Composable
fun Calculator(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(text = "Calculator")
    }
}
