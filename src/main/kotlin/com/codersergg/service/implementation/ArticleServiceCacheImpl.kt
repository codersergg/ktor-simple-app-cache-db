package com.codersergg.service.implementation

import com.codersergg.model.AbstractArticleWith
import com.codersergg.model.ArticleToAdd
import com.codersergg.model.CustomerResponse
import com.codersergg.service.ArticleService
import org.ehcache.PersistentCacheManager

class ArticleServiceCacheImpl(
    articleServiceImpl: ArticleServiceImpl,
    init: PersistentCacheManager,
    alias: String
) : ArticleService {
    override suspend fun allArticles(): List<AbstractArticleWith<Int>> {
        TODO("Not yet implemented")
    }

    override suspend fun allArticlesWithCustomers(): List<AbstractArticleWith<CustomerResponse>> {
        TODO("Not yet implemented")
    }

    override suspend fun article(id: Int): AbstractArticleWith<Int>? {
        TODO("Not yet implemented")
    }

    override suspend fun addNewArticle(article: ArticleToAdd): AbstractArticleWith<Int>? {
        TODO("Not yet implemented")
    }

    override suspend fun editArticleLevel8(
        id: Int,
        title: String,
        body: String,
        customerId: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun editArticle(
        id: Int,
        title: String,
        body: String,
        customerId: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArticle(id: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun updateIfTitleByCustomerId(
        id: Int,
        customerId: Int
    ): AbstractArticleWith<Int>? {
        TODO("Not yet implemented")
    }
}