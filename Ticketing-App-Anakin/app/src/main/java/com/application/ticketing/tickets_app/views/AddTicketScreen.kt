package com.application.ticketing.tickets_app.views

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.application.ticketing.tickets_app.R
import com.application.ticketing.tickets_app.data.database.Ticket
import com.application.ticketing.tickets_app.data.viewmodel.TicketsViewModel
import com.application.ticketing.tickets_app.views.utils.CustomButton
import com.application.ticketing.tickets_app.views.utils.CustomUpdateButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTicketScreen(ticketsViewModel: TicketsViewModel) {
    val context = LocalContext.current
    var filmName by rememberSaveable { mutableStateOf("") }
    var seat by rememberSaveable { mutableStateOf("") }
    var category by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var isSeatDropDownExpanded by remember { mutableStateOf(false) }
    var isCategoryDropDownExpanded by remember { mutableStateOf(false) }
    var isPriceDropDownExpanded by remember { mutableStateOf(false) }
    val seatList = listOf("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9","B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9","C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9")
    val categoryList = listOf("Regular ", "Starium", "SweetBox", "Gold Class", "Satin Class", "Velvet Class", "SphereX", "ScreenX")
    val priceList = listOf("Rp. 50,000 ", "Rp. 100,000", "Rp. 150,000", "Rp. 200,000", "Rp. 250,000", "Rp.300,000", "Rp. 350,000", "Rp. 400.000")


    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 60.dp),
        contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Add Your Ticket", fontSize = 30.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = filmName,
                label = { Text(stringResource(id = R.string.film_name)) },
                onValueChange = {
                    filmName = it
                })
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    OutlinedTextField(
                        value = seat,
                        onValueChange = { seat = it },
                        placeholder = { androidx.compose.material.Text(text = "Choose Your Seat") },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isSeatDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowCircleDown, "") }
                    )

                    DropdownMenu(
                        expanded = isSeatDropDownExpanded,
                        onDismissRequest = { isSeatDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    ) {
                        seatList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                seat = selectedItem
                                isSeatDropDownExpanded = false
                            }) {
                                androidx.compose.material.Text(selectedItem)
                            }
                            if (index != seatList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    OutlinedTextField(
                        value = category,
                        onValueChange = { category = it },
                        placeholder = { androidx.compose.material.Text(text = "Choose The Category") },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isCategoryDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowCircleDown, "") }
                    )

                    DropdownMenu(
                        expanded = isCategoryDropDownExpanded,
                        onDismissRequest = { isCategoryDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    ) {
                        categoryList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                category = selectedItem
                                isCategoryDropDownExpanded = false
                            }) {
                                androidx.compose.material.Text(selectedItem)
                            }
                            if (index != categoryList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {
                    OutlinedTextField(
                        value = price,
                        onValueChange = { price = it },
                        placeholder = { androidx.compose.material.Text(text = "Choose The Price") },
                        enabled = false,
                        modifier = Modifier
                            .clickable {
                                isPriceDropDownExpanded = true
                            }
                            .fillMaxWidth(0.8f),
                        textStyle = TextStyle(color = Color.Black),
                        trailingIcon = { Icon(imageVector = Icons.Default.ArrowCircleDown, "") }
                    )

                    DropdownMenu(
                        expanded = isPriceDropDownExpanded,
                        onDismissRequest = { isPriceDropDownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                    ) {
                        priceList.forEachIndexed { index, selectedItem ->
                            DropdownMenuItem(onClick = {
                                price = selectedItem
                                isPriceDropDownExpanded = false
                            }) {
                                androidx.compose.material.Text(selectedItem)
                            }
                            if (index != priceList.lastIndex)
                                Divider(Modifier.background(Color.Black))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            CustomButton(stringResource(id = R.string.add_ticket)) {
                // Create the ticket object
                if (filmName == "" || seat == "" || category == "" || price == ""  )  {
                    Toast.makeText(context, "Ticket Added Failed", Toast.LENGTH_SHORT).show()
                    Log.d("data db", "Data Gagal")
                } else {
                    val ticket = Ticket(filmName, seat, category, price)
                    Log.d("data db", "Data Berhasil $ticket")

                    // Update the ticket to the database
                    ticketsViewModel.addTicket(ticket)
                    // Clear text fields
                    filmName = ""
                    seat = ""
                    category = ""
                    price = ""
                    Toast.makeText(context, "Ticket added successfully", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
