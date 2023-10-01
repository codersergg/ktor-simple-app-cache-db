package com.codersergg.dao.schema

import com.codersergg.model.AbstractArticleWith
import com.codersergg.model.ArticleWintCustomer
import com.codersergg.model.ArticleWintCustomerId
import com.codersergg.model.CustomerResponse
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object Articles : IntIdTable() {
    val title = varchar("title", 128)
    val body = varchar("body", 1024)
    val customer = reference("customer", Customers)
}

class Article(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<Article>(Articles)

    var title by Articles.title
    var body by Articles.body
    var customer by Customer referencedOn Articles.customer

    fun toArticleWintCustomerId(): AbstractArticleWith<Int> {
        return ArticleWintCustomerId(id.value, title, body, customer.id.value)
    }

    fun toArticleWintCustomer(): AbstractArticleWith<CustomerResponse> {
        return ArticleWintCustomer(id.value, title, body, customer.toCustomerResponse())
    }
}