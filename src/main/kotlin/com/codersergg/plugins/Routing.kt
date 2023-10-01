package com.codersergg.plugins

import com.codersergg.routes.articleRouting
import com.codersergg.routes.customerRouting
import com.codersergg.service.ArticleService
import com.codersergg.service.CustomerService
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(articleService: ArticleService, customerService: CustomerService) {
    routing {
        articleRouting(articleService)
        customerRouting(customerService)
    }
}
