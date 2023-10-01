package com.codersergg.dao.schema

import com.codersergg.model.CustomerResponse
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object Customers : IntIdTable() {
    val firstName: Column<String> = text("first_name")
    val lastName: Column<String> = text("last_name")
    val email: Column<String> = text("email").uniqueIndex()
}

class Customer(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Customer>(Customers)

    var firstName by Customers.firstName
    var lastName by Customers.lastName
    var email by Customers.email

    fun toCustomerResponse(): CustomerResponse {
        return CustomerResponse(id.value, firstName, lastName, email)
    }
}