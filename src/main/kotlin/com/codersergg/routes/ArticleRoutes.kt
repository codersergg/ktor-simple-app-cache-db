package com.codersergg.routes

import com.codersergg.model.ArticleToAdd
import com.codersergg.model.ArticleWintCustomerId
import com.codersergg.service.ArticleService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.articleRouting(articleService: ArticleService) {

    route("articles") {
        get {
            call.respond(HttpStatusCode.OK, articleService.allArticles())
        }
        post {
            val articleToAdd = call.receive<ArticleToAdd>()
            articleService.addNewArticle(articleToAdd)
            call.respond(HttpStatusCode.Created)
        }
        get("{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respond(HttpStatusCode.OK, articleService.article(id) as ArticleWintCustomerId)
        }
        get("{id}/edit") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            call.respond(HttpStatusCode.OK, articleService.article(id) as ArticleWintCustomerId)
        }
        post("{id}") {
            val id = call.parameters.getOrFail<Int>("id").toInt()
            val formParameters = call.receiveParameters()
            when (formParameters.getOrFail("_action")) {
                "update" -> {
                    val title = formParameters.getOrFail("title")
                    val body = formParameters.getOrFail("body")
                    val customerId = formParameters.getOrFail("customer_id").toInt()
                    call.respond(HttpStatusCode.OK, articleService.editArticle(id, title, body, customerId))
                }

                "delete" -> {
                    call.respond(HttpStatusCode.OK, articleService.deleteArticle(id))
                }
            }
        }
    }
}