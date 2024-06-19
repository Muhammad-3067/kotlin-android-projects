package com.rdmm.expensetracker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

// MainContent() calls inside the AppBar.kt
@Composable
fun MainContent(padding: PaddingValues, navcontroller: NavController) {
    Column(modifier = Modifier
        .padding(padding)) {
            Card(
                modifier = Modifier
                    .padding(10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 12.dp, 10.dp, 8.dp,),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Расходы",
                        fontSize = 18.sp
                    )
                    Text(text = "Rub", fontSize = 16.sp)
                }
                Divider (
                    color = Color.White,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth(0.95f)
                        .align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 8.dp, 10.dp, 12.dp,),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "За все время", fontSize = 18.sp)
                    Text(text = "20.00", fontSize = 16.sp)
                }

            }
            Card(
                modifier = Modifier
                    .padding(10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 12.dp, 10.dp, 8.dp,),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Доходы", fontSize = 18.sp)
                    Text(text = "Rub", fontSize = 16.sp)
                }
                Divider (
                    color = Color.White,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth(0.95f)
                        .align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 8.dp, 10.dp, 12.dp,),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "За все время", fontSize = 18.sp)
                    Text(text = "30.00", fontSize = 16.sp)
                }

            }
            Card(
                modifier = Modifier
                    .padding(10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 12.dp, 10.dp, 8.dp,),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Баланс", fontSize = 18.sp)
                    Text(text = "Rub", fontSize = 16.sp)
                }
                Divider (
                    color = Color.White,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth(0.95f)
                        .align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 8.dp, 10.dp, 12.dp,),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "За все время", fontSize = 18.sp)
                    Text(text = "20.00", fontSize = 16.sp)
                }

            }
            ExpensesTables(navcontroller)
            FloatingActionBtn{ navcontroller.navigate(Screen.AddExpenseScreen.route)}
    }
}