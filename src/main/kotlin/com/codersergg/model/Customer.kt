package com.codersergg.model

import java.io.Serializable

abstract class AbstractCustomer : Serializable {
    abstract val firstName: String
    abstract val lastName: String
    abstract val email: String
}

data class CustomerToAdd(
    override val firstName: String,
    override val lastName: String,
    override val email: String
) : AbstractCustomer(), Serializable

abstract class AbstractCustomerResponse : AbstractCustomer(), Serializable {
    abstract val id: Int
    abstract override val firstName: String
    abstract override val lastName: String
    abstract override val email: String
}

data class CustomerResponse(
    override val id: Int,
    override val firstName: String,
    override val lastName: String,
    override val email: String
) : AbstractCustomerResponse(), Serializable