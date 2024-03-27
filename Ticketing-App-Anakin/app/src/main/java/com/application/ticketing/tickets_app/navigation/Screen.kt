package com.application.ticketing.tickets_app.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen("main_screen")
    object AddTicketScreen : Screen("add_a_ticket")
    object TicketDetailsScreen : Screen("ticket_details")
    object AllTicketsScreen : Screen("all_tickets")
}
