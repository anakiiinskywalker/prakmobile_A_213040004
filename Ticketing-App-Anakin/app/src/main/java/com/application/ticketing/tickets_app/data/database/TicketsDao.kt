package com.application.ticketing.tickets_app.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TicketsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTicket(ticket: Ticket)

    @Query("SELECT * FROM ticket WHERE id = :id")
    fun getTicket(id: Int): Ticket

    @Query("SELECT * FROM ticket")
    fun getAllTickets(): List<Ticket>

    @Delete
    fun deleteTicket(ticket: Ticket)

    @Query("UPDATE ticket SET name = :filmName, seat = :seat, category = :category, price = :price WHERE id = :id")
    fun updateTicket(id: Int, filmName: String, seat: String, category: String, price: String)
}