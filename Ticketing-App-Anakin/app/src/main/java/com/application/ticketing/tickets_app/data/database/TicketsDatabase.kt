package com.application.ticketing.tickets_app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Ticket::class],
    version = 1
)
abstract class TicketsDatabase : RoomDatabase() {

    abstract fun ticketsDao(): TicketsDao

    companion object {
        @Volatile
        private var INSTANCE: TicketsDatabase? = null

        fun getInstance(context: Context): TicketsDatabase {
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TicketsDatabase::class.java,
                    "ticket_db"
                ).build()
                INSTANCE = instance
                instance
            }
            return INSTANCE as TicketsDatabase
        }
    }
}