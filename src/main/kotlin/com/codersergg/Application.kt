package com.codersergg

import com.codersergg.plugins.configureDatabases
import com.codersergg.plugins.configureRouting
import com.codersergg.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureSerialization()
    configureDatabases()
    configureRouting()
}
