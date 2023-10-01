package com.codersergg.dao

import com.codersergg.dao.schema.Article
import com.codersergg.dao.schema.Articles
import com.codersergg.dao.schema.Customer
import com.codersergg.dao.schema.Customers
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.sql.Connection.TRANSACTION_REPEATABLE_READ
import java.sql.Connection.TRANSACTION_SERIALIZABLE

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val driverClassName = config.property("storage.driverClassName").getString()
        val jdbcURL = config.property("storage.jdbcURL").getString() +
                (config.propertyOrNull("storage.dbFilePath")?.getString()?.let {
                    File(it).canonicalFile.absolutePath
                } ?: "")
        val database =
            Database.connect(createHikariDataSource(url = jdbcURL, driver = driverClassName))
        transaction(database, createSchema())
    }

    fun initTest(config: ApplicationConfig) {
        val driverClassName = config.property("storage-test.driverClassName").getString()
        val jdbcURL = config.property("storage-test.jdbcURL").getString() +
                (config.propertyOrNull("storage-test.dbFilePath")?.getString()?.let {
                    File(it).canonicalFile.absolutePath
                } ?: "")
        val database =
            Database.connect(createHikariDataSource(url = jdbcURL, driver = driverClassName))
        transaction(database, createSchema())
        initTestSchema()
    }

    private fun createSchema(): Transaction.() -> Unit = {
        SchemaUtils.create(Articles, Customers)
    }

    private fun initTestSchema() {
        val customer = Customer.new {
            firstName = "firstName"
            lastName = "lastName"
            email = "test@test.com"
        }
        Article.new {
            title = "title"
            body = "body"
            this.customer = customer
        }
    }

    private fun createHikariDataSource(
        url: String,
        driver: String
    ) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = TRANSACTION_REPEATABLE_READ.toString()
        validate()
    })

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

    suspend fun <T> dbQuerySerializable(block: suspend () -> T): T =
        newSuspendedTransaction(
            context = Dispatchers.IO,
            transactionIsolation = TRANSACTION_SERIALIZABLE
        ) { block() }
}