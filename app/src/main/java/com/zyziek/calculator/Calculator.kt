package com.zyziek.calculator

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


val calculatorButtons = listOf(
    "C", "(", ")", "÷",
    "7", "8", "9", "×",
    "4", "5", "6", "-",
    "1", "2", "3", "+",
    "AC", "0", ",", "="
)

/*
    Modifier - let's us decorate and add behaviour to elements (background, padding, font etc)
 */
@Composable
fun Calculator(modifier: Modifier = Modifier, viewModel: CalculatorViewModel) {
    val equationText = viewModel.equationText.observeAsState()
    val resultText = viewModel.resultText.observeAsState()


    Box(modifier = modifier) {
        Column (
            modifier = modifier.fillMaxSize().background(Color(0xFF1C1C1C)),
            //Text is on the right side
            horizontalAlignment = Alignment.End
        ){
            Column(
                modifier = Modifier
                    .weight(1f) // Blok "wyświetlacza" zajmuje część ekranu
                    .fillMaxSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom // Tekst na dole "wyświetlacza"
            ) {
                Text(
                    text = equationText.value ?: "",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 30.sp,
                        textAlign = TextAlign.End
                    ),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis
                )
                if (equationText.value == "6*6") {
                    SpecialEquationText()
                } else {
                    Text(
                        text = resultText.value ?: "",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 60.sp,
                            textAlign = TextAlign.End
                        ),
                        maxLines = 2,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

            }

            LazyVerticalGrid (
                columns = GridCells.Fixed(4),
            ) {

                items(calculatorButtons) {
                    CalculatorButton(btn = it, onClick = {
                        viewModel.onButtonClick(it)
                    })
                }

            }

        }
    }
}

@Composable
fun SpecialEquationText() {
    val context = LocalContext.current
    val textToDisplay1 = "yyy szesc razy szesc"
    val textToDisplay2 = "to jest najgorsze"
    val displayedText = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {

        val mediaPlayer = MediaPlayer.create(context, R.raw.bedoes_sound_effect)
        mediaPlayer.start()

        displayedText.value = ""
        for (i in textToDisplay1.indices) {
            displayedText.value = textToDisplay1.substring(0, i + 1)
            delay(100)
        }

        delay(500)
        displayedText.value = ""

        delay( 2500)
        for (i in textToDisplay2.indices) {
            displayedText.value = textToDisplay2.substring(0, i + 1)
            delay(50)
        }

        delay(400)
        mediaPlayer.setOnCompletionListener {
            it.release()
        }

        displayedText.value = "36"
    }

    Text(
        text = displayedText.value,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        style = TextStyle(
            color = Color.White,
            fontSize = 60.sp
        )
    )
}


@Composable
fun CalculatorButton(btn: String, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(8.dp)) {
        FloatingActionButton(
            modifier = Modifier.size(80.dp),
            onClick = onClick,
            contentColor = Color.White,
            containerColor = getColor(btn),
            shape = CircleShape
        ) {
            Text(text = btn, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        }
    }
}

fun getColor(btn: String): Color {
    if (btn in listOf("C", "(", ")")) {
        return Color(0xFFD4D4D2)
    }

    if(btn in listOf("÷","×", "-", "+",  "=")) {
        return Color(0xFFFF9500)
    }

    return Color(0xFF505050)
}