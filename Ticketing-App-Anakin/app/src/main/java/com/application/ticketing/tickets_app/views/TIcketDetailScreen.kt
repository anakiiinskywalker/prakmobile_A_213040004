package com.application.ticketing.tickets_app.views

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowCircleDown
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.application.ticketing.tickets_app.R
import com.application.ticketing.tickets_app.data.database.Ticket
import com.application.ticketing.tickets_app.data.viewmodel.TicketsViewModel
import com.application.ticketing.tickets_app.navigation.Screen
import com.application.ticketing.tickets_app.views.utils.CustomButton
import com.application.ticketing.tickets_app.views.utils.CustomDeleteButton
import com.application.ticketing.tickets_app.views.utils.CustomUpdateButton
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketDetailScreen(id: Int, ticketsViewModel: TicketsViewModel, navController: NavController) {
    val context = LocalContext.current
    val tickets = remember { mutableStateOf<Ticket?>(null) }
    var filmNameState: String? by remember { mutableStateOf(null) }
    var seatState: String? by remember { mutableStateOf(null) }
    var categoryState: String? by remember { mutableStateOf(null) }
    var priceState: String? by remember { mutableStateOf(null) }
    var isSeatDropDownExpanded by remember { mutableStateOf(false) }
    var isCategoryDropDownExpanded by remember { mutableStateOf(false) }
    var isPriceDropDownExpanded by remember { mutableStateOf(false) }
    val seatList = listOf(
        "A1",
        "A2",
        "A3",
        "A4",
        "A5",
        "A6",
        "A7",
        "A8",
        "A9",
        "B1",
        "B2",
        "B3",
        "B4",
        "B5",
        "B6",
        "B7",
        "B8",
        "B9",
        "C1",
        "C2",
        "C3",
        "C4",
        "C5",
        "C6",
        "C7",
        "C8",
        "C9"
    )
    val categoryList = listOf(
        "Regular ",
        "Starium",
        "SweetBox",
        "Gold Class",
        "Satin Class",
        "Velvet Class",
        "SphereX",
        "ScreenX"
    )
    val priceList = listOf(
        "Rp. 50,000 ",
        "Rp. 100,000",
        "Rp. 150,000",
        "Rp. 200,000",
        "Rp. 250,000",
        "Rp.300,000",
        "Rp. 350,000",
        "Rp. 400.000"
    )


    LaunchedEffect(Unit) {
        ticketsViewModel.getTicket(id)
    }
    ticketsViewModel.getTicket(id)

    val ticket = ticketsViewModel.getTicket.observeAsState().value
    ticket ?: return
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                Text(
                    text = "Details Ticket",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            AsyncImage(
                model = "https://source.unsplash.com/random/300x300/?movie",
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                contentScale = ContentScale.FillBounds,
            )
            Spacer(modifier = Modifier.height(20.dp))

            Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                OutlinedTextField(
                    value = filmNameState
                        ?: ticket.filmName,  // display database text if no modified text available
                    onValueChange = { filmNameState = it },  // save any changes into nameState
                    label = { Text(stringResource(id = R.string.film_name)) },
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    OutlinedTextField(
                        value = seatState ?: ticket.seat,
                        onValueChange = { seatState = it },
                        placeholder = { androidx.compose.material.Text(text = ticket.seat) },
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
                                seatState = selectedItem
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
            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Box {
                    OutlinedTextField(
                        value = categoryState ?: ticket.category,
                        onValueChange = { categoryState = it },
                        placeholder = { androidx.compose.material.Text(text = ticket.category) },
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
                                categoryState = selectedItem
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

            Spacer(modifier = Modifier.height(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box {
                    OutlinedTextField(
                        value = priceState ?: ticket.price,
                        onValueChange = { priceState = it },
                        placeholder = { androidx.compose.material.Text(text = ticket.price) },
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
                                priceState = selectedItem
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

            Spacer(modifier = Modifier.height(15.dp))

            Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                CustomUpdateButton(stringResource(id = R.string.update_ticket))
                {
                    // Create the ticket object
                    val ticket = Ticket(
                        filmName = filmNameState ?: ticket.filmName,
                        seat = seatState ?: ticket.seat,
                        category = categoryState ?: ticket.category,
                        price = priceState ?: ticket.price
                    )

                    // Update the ticket in the database
                    ticketsViewModel.updateTicket(
                        id,
                        ticket.filmName,
                        ticket.seat,
                        ticket.category,
                        ticket.price
                    )
                    Toast.makeText(context, "Ticket updated successfully", Toast.LENGTH_SHORT)
                        .show()

                }
                Column(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    val openDialog = remember { mutableStateOf(false) }

                    Button(onClick = { openDialog.value = true }) {
                        Text(text = "Delete Ticket")
                    }

                    if (openDialog.value) {
                        AlertDialog(
                            onDismissRequest = { openDialog.value = false },
                            title = {
                                Text(text = "Deleting Ticket")
                            },
                            text = {
                                Text(text = "Do you really want to Delete this Ticket ?")
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        ticket?.let { id ->
                                            ticketsViewModel.deleteTicket(id)
                                        }
                                        openDialog.value = false
                                        Toast.makeText(context, "Ticket Deleted successfully", Toast.LENGTH_SHORT)
                                            .show()
                                        navController.navigate(Screen.AllTicketsScreen.route)
                                    },
                                ) {
                                    Text(text = "CONFIRM")

                                }
                            },
                            dismissButton = {
                                Button(onClick = { openDialog.value = false }
                                ) {
                                    Text(text = "CANCEL")
                                }
                            }
                        )
                    }
                }

            }

        }

    }
}



