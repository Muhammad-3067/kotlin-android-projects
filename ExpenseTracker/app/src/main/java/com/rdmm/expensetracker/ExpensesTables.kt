package com.rdmm.expensetracker

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// ExpensesTables() calls inside the MainContent.kt
@Composable
fun ExpensesTables(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly

    ) {
        Card(
            modifier = Modifier
                .padding(0.dp, 40.dp, 0.dp, 0.dp)
                .clickable {
                    navController.navigate(Screen.Expenses.route)
                },
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.background_light)
        )) {
            Image(
                painter = painterResource(id = R.drawable.baseline_shopping),
                contentDescription = "shopping",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(12.dp))
            Text(text = "Все расходы", modifier = Modifier.padding(12.dp))
        }
        Card(
            modifier = Modifier
                .padding(0.dp, 40.dp, 0.dp, 0.dp)
                .clickable {
                    navController.navigate(Screen.Incomes.route)
                },
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.background_light)
            )) {
            Image(
                painter = painterResource(id = R.drawable.baseline_money),
                contentDescription = "money",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(12.dp))
            Text(text = "Все доходы", modifier = Modifier.padding(12.dp))
        }
    }
}