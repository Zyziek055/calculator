package com.zyziek.calculator

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp


val calculatorButtons = listOf(
    "C", "←", "%", "÷",
    "7", "8", "9", "×",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "0", ".", "=", ""
)

/*
    Modifier - let's us decorate and add behaviour to elements (background, padding, font etc)
 */
@Composable
fun Calculator(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Column (
            modifier = modifier.fillMaxSize(),
            //Text is on the right side
            horizontalAlignment = Alignment.End
        ){
            Text(
                text = "123+123",
                style = TextStyle(
                    fontSize = 30.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
                )

            Text(
                text = "234+123",
                style = TextStyle(
                    fontSize = 60.sp,
                    textAlign = TextAlign.End
                ),
                maxLines = 2,
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyVerticalGrid (
                columns = GridCells.Fixed(4),
            ) {

                items(calculatorButtons) {
                    Text(text = it)
                }

            }

        }
    }
}
