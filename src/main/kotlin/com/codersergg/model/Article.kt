package com.codersergg.model

import com.codersergg.dao.Cacheable
import java.io.Serializable

abstract class AbstractArticle : Serializable {
    abstract val title: String
    abstract val body: String
}

data class ArticleToAdd(
    override val title: String,
    override val body: String,
    val customerId: Int
) : AbstractArticle(), Serializable

abstract class AbstractArticleWith<T> : AbstractArticle(), Serializable {
    abstract val id: Int
    abstract override val title: String
    abstract override val body: String
    abstract fun customer(): T
}

data class ArticleWintCustomerId(
    override val id: Int,
    override val title: String,
    override val body: String,
    val customerId: Int
) : AbstractArticleWith<Int>(), Serializable, Cacheable {
    override fun customer() = customerId
}

data class ArticleWintCustomer(
    override val id: Int,
    override var title: String,
    override val body: String,
    val customer: CustomerResponse
) : AbstractArticleWith<CustomerResponse>(), Serializable {
    override fun customer() = customer
}