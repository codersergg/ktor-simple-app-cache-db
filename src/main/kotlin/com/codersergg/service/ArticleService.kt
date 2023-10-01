package com.codersergg.service

import com.codersergg.model.AbstractArticleWith
import com.codersergg.model.ArticleToAdd
import com.codersergg.model.CustomerResponse

interface ArticleService {
    suspend fun allArticles(): List<AbstractArticleWith<Int>>
    suspend fun allArticlesWithCustomers(): List<AbstractArticleWith<CustomerResponse>>
    suspend fun article(id: Int): AbstractArticleWith<Int>?
    suspend fun addNewArticle(article: ArticleToAdd): AbstractArticleWith<Int>?
    suspend fun editArticleLevel8(id: Int, title: String, body: String, customerId: Int): Boolean
    suspend fun editArticle(id: Int, title: String, body: String, customerId: Int): Boolean
    suspend fun deleteArticle(id: Int): Boolean
    suspend fun updateIfTitleByCustomerId(id: Int, customerId: Int): AbstractArticleWith<Int>?
}