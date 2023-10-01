package com.codersergg

import com.codersergg.dao.CacheManager
import com.codersergg.dao.DatabaseFactory
import com.codersergg.model.ArticleWintCustomerId
import com.codersergg.plugins.configureRouting
import com.codersergg.plugins.configureSerialization
import com.codersergg.service.ArticleService
import com.codersergg.service.CustomerService
import com.codersergg.service.implementation.ArticleServiceCacheImpl
import com.codersergg.service.implementation.ArticleServiceImpl
import com.codersergg.service.implementation.CustomerServiceImpl
import io.ktor.server.application.*
import io.ktor.server.netty.*
import java.io.File

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()

    val config = environment.config
    DatabaseFactory.init(config)
    val alias = "articlesCache"
    val articleService: ArticleService =  ArticleServiceCacheImpl(
        ArticleServiceImpl(),
        CacheManager.init(
            File(environment.config.property("storage.ehcacheFilePath").getString()),
            alias,
            ArticleWintCustomerId::class.java
        ),
        alias
    )
    val customerService: CustomerService = CustomerServiceImpl()

    configureRouting(articleService, customerService)
}
