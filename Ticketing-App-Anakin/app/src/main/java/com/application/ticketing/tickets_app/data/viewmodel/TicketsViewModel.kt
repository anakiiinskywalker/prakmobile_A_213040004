package com.application.ticketing.tickets_app.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.application.ticketing.tickets_app.data.database.Ticket
import com.application.ticketing.tickets_app.data.database.TicketsDao
import com.application.ticketing.tickets_app.data.database.TicketsDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TicketsViewModel(application: Application) : AndroidViewModel(application) {

    private val dao: TicketsDao
    private val _allTickets = MutableLiveData<List<Ticket>>()
    private val _getTicket = MutableLiveData<Ticket>()

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    val allTickets: LiveData<List<Ticket>> = _allTickets
    val getTicket: LiveData<Ticket> = _getTicket

    init {
        dao = TicketsDatabase.getInstance(application).ticketsDao()
    }

    fun getTickets() {
        viewModelScope.launch(Dispatchers.IO) {
            _allTickets.postValue(dao.getAllTickets())
        }
    }

    fun getTicket(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _getTicket.postValue(dao.getTicket(id))
        }
    }

    fun addTicket(ticket: Ticket) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {dao.insertTicket(ticket)}
        }
        getTickets()
    }

    fun deleteTicket(ticket: Ticket) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {dao.deleteTicket(ticket)}
        }
        getTickets()
    }

    fun updateTicket(id: Int, filmName: String, seat: String, category: String, price: String) {
        viewModelScope.launch(Dispatchers.IO) {
            executorService.execute {
                dao.updateTicket(id, filmName, seat, category, price)
            }
        }
        getTickets()
    }
}