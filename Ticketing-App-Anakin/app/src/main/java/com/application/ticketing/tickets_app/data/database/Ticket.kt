package com.application.ticketing.tickets_app.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ticket")
data class Ticket(
    @ColumnInfo(name = "name")
    var filmName: String,

    @ColumnInfo(name = "seat")
    var seat: String,

    @ColumnInfo(name = "category")
    var category: String,

    @ColumnInfo(name = "price")
    var price: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)