package com.codersergg.service

import com.codersergg.model.AbstractCustomer
import com.codersergg.model.CustomerResponse
import com.codersergg.model.CustomerToAdd

interface CustomerService {
    suspend fun allCustomers(): List<AbstractCustomer>
    suspend fun customer(id: Int): AbstractCustomer?
    suspend fun addNewCustomer(customer: CustomerToAdd): CustomerResponse?
    suspend fun editCustomerLevel8(customer: CustomerResponse): Boolean
    suspend fun editCustomer(customer: CustomerResponse): Boolean
    suspend fun deleteCustomer(id: Int): Boolean
}