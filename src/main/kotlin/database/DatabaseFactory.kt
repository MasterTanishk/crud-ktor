package com.example.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource

class DatabaseFactory {
    fun createDataSource():DataSource{
        val config = HikariConfig().apply{
            jdbcUrl = "jdbc:postgresql://localhost:5433/newktordi"
            username = "postgres"
            password = "password"
            driverClassName = "org.postgresql.Driver"
        }
        return HikariDataSource(config)
    }
}