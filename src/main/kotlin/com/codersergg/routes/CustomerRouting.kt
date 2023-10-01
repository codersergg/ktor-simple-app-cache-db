package com.codersergg.routes

import com.codersergg.service.CustomerService
import io.ktor.server.routing.*

fun Route.customerRouting(customerService: CustomerService) {
    route("/customer") {
        get {

        }
        get("{id?}") {

        }
        post {

        }
        delete("{id?}") {

        }
    }
}