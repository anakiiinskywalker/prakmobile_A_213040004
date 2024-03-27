package com.application.ticketing.tickets_app.views.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(buttonText: String, onclick: () -> Unit) {
    Button(onClick = onclick) {
        Text(
            text = buttonText
        )
    }
}

@Composable
fun CustomUpdateButton(buttonText: String, onclick: () -> Unit) {
    Button(onClick = onclick) {
        Text(
            text = buttonText
        )
    }
}

@Composable
fun CustomDeleteButton(buttonText: String, onclick: () -> Unit) {
    Button(onClick = onclick) {
        Text(
            text = buttonText
        )
    }
}

@Composable
fun TitleText(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp),
        textAlign = TextAlign.Center,
        fontFamily = FontFamily.Monospace,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold
    )
}
