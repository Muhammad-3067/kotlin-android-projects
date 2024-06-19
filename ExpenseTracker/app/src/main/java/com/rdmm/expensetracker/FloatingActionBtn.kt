package com.rdmm.expensetracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// FloatingActionBtn() calls inside the MainContent.kt
@Composable
fun FloatingActionBtn(onClick: () -> Unit ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 50.dp, 20.dp, 0.dp),
        horizontalArrangement = Arrangement.Absolute.Right
    ) {
        FloatingActionButton(
            onClick = { onClick() },
            shape = CircleShape,
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Floating action button")
        }
    }
}