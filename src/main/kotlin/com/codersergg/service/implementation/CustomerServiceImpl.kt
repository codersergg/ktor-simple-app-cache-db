package com.codersergg.service.implementation

import com.codersergg.model.AbstractCustomer
import com.codersergg.model.CustomerResponse
import com.codersergg.model.CustomerToAdd
import com.codersergg.service.CustomerService

class CustomerServiceImpl: CustomerService {
    override suspend fun allCustomers(): List<AbstractCustomer> {
        TODO("Not yet implemented")
    }

    override suspend fun customer(id: Int): AbstractCustomer? {
        TODO("Not yet implemented")
    }

    override suspend fun addNewCustomer(customer: CustomerToAdd): CustomerResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun editCustomerLevel8(customer: CustomerResponse): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun editCustomer(customer: CustomerResponse): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCustomer(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}