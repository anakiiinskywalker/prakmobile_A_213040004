package com.application.ticketing.tickets_app.views

import HandleBackPress
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.application.ticketing.tickets_app.R
import com.application.ticketing.tickets_app.data.database.Ticket
import com.application.ticketing.tickets_app.data.viewmodel.TicketsViewModel
import com.application.ticketing.tickets_app.navigation.Screen
import com.application.ticketing.tickets_app.views.utils.TitleText
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment


@Composable
fun AllTicketsScreen(navController: NavController, ticketsViewModel: TicketsViewModel) {
    val tickets: List<Ticket> by ticketsViewModel.allTickets.observeAsState(initial = listOf())
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(Unit) {
        ticketsViewModel.getTickets()
    }

    LaunchedEffect(Unit) {
        ticketsViewModel.getTickets()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        if (tickets.isEmpty()) {
            TitleText(text = stringResource(id = R.string.all_tickets_title), modifier = Modifier,)
            EmptyContent()
        } else {
            TitleText(text = stringResource(id = R.string.all_tickets_title), modifier = Modifier,)
            LazyColumn(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(15.dp)) {
                items(tickets) { ticket ->
                    OutlinedCard(
                        Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickable {
                                // Navigate to ticket details
                                navController.navigate(route = Screen.TicketDetailsScreen.route + "/" + ticket.id)
                            },
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(10.dp)
                        ) {
                            AsyncImage(
                                model = "https://source.unsplash.com/random/300x300/?movie",
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .clip(shape = RoundedCornerShape(10.dp))
                            )
                            Column {
                                Text(
                                    text = ticket.filmName,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp, 0.dp, 0.dp, 5.dp),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 30.sp
                                )
                                Text(
                                    text = "Seat - ${ticket.seat}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp, 0.dp, 0.dp, 5.dp),
                                    fontStyle = FontStyle.Italic
                                )
                                Text(
                                    text = "Category - ${ticket.category}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp, 0.dp, 0.dp, 5.dp),
                                    fontSize = 14.sp
                                )
                                Text(
                                    text = "${ticket.price}",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp, 0.dp, 0.dp, 0.dp),
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (backDispatcher != null) {
        HandleBackPress(backDispatcher) {
            navController.navigate(Screen.HomeScreen.route)
        }
    }
}

@Composable
fun EmptyContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                MaterialTheme.colors.background
            ),
    ) {
        Icon(
            modifier = Modifier.size(120.dp),
            painter = painterResource(
                R.drawable.no_movie
            ), contentDescription = stringResource(
                R.string.no_ticket
            ),
            tint = Color.LightGray
        )
        androidx.compose.material.Text(
            text = stringResource(
                R.string.text_empty_content
            ),
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            color = Color.LightGray
        )
    }
}
